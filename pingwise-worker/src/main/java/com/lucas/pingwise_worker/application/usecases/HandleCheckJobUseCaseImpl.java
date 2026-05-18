package com.lucas.pingwise_worker.application.usecases;

import com.lucas.pingwise_worker.application.mappers.AlertMapper;
import com.lucas.pingwise_worker.application.ports.in.HandleCheckJobUseCase;
import com.lucas.pingwise_worker.application.ports.in.dto.HandleCheckJobCommand;
import com.lucas.pingwise_worker.application.ports.out.*;
import com.lucas.pingwise_worker.domain.enums.IncidentStatus;
import com.lucas.pingwise_worker.domain.model.Check;
import com.lucas.pingwise_worker.domain.model.Incident;
import com.lucas.pingwise_worker.domain.model.MonitorUpdate;
import com.lucas.pingwise_worker.infrastructure.http.TimingInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class HandleCheckJobUseCaseImpl implements HandleCheckJobUseCase {

    private final AlertMapper alertMapper;

    private final TimingInterceptor timingInterceptor;

    private final RestClient restClient;
    private final CheckRepository checkRepository;
    private final IncidentRepository incidentRepository;

    private final CachePort cachePort;
    private final MonitorProducerPort monitorProducerPort;
    private final AlertProducerPort alertProducerPort;

    @Override
    public void execute(HandleCheckJobCommand command) {

        RetryTemplate retryTemplate = RetryTemplate.builder()
                .maxAttempts(command.consecutiveFailuresThreshold())
                .fixedBackoff(command.timeoutMs())
                .retryOn(RestClientException.class)
                .build();

        try {
            retryTemplate.execute(context -> {
                log.info("Processing monitor={}, attempt={}", command.monitorId(), context.getRetryCount() + 1);
                process(command);
                return null;
            });
        } catch (Exception ex) {
            log.error("Failed to process monitor={} after retries. error={}",
                    command.monitorId(),
                    ex.getMessage()
            );
            processCheckResult(command.monitorId(), false, command.consecutiveFailuresThreshold());
        }
    }

    private void process(HandleCheckJobCommand command) {

        try {
            var result = this.restClient.get()
                    .uri(command.url())
                    .retrieve()
                    .toEntity(String.class);

            boolean isSuccess = !result.getStatusCode().isError();

            saveCheck(
                    command.monitorId(),
                    LocalDateTime.now(),
                    (int) timingInterceptor.getResponseMs(),
                    result.getStatusCode().value(),
                    isSuccess,
                    "",
                    Optional.empty()
            );

            processCheckResult(command.monitorId(), isSuccess, command.consecutiveFailuresThreshold());

            if (!isSuccess) {
                throw new RestClientException("HTTP error status: " + result.getStatusCode());
            }

        } catch (RestClientResponseException ex) {

            saveCheck(
                    command.monitorId(),
                    LocalDateTime.now(),
                    (int) timingInterceptor.getResponseMs(),
                    ex.getStatusCode().value(),
                    false,
                    "",
                    Optional.of(ex.getResponseBodyAsString())
            );

            processCheckResult(command.monitorId(), false, command.consecutiveFailuresThreshold());

            throw ex;

        } catch (RestClientException ex) {

            saveCheck(
                    command.monitorId(),
                    LocalDateTime.now(),
                    (int) timingInterceptor.getResponseMs(),
                    0,
                    false,
                    "",
                    Optional.ofNullable(ex.getMessage())
            );

            processCheckResult(command.monitorId(), false, command.consecutiveFailuresThreshold());

            throw ex;
        }
    }

    public void saveCheck(
            UUID monitorId,
            LocalDateTime checkedAt,
            Integer responseMs,
            Integer httpStatus,
            boolean isUp,
            String region,
            Optional<String> errorMessage
    ) {
        var check = Check.builder()
                .id(UUID.randomUUID())
                .monitorId(monitorId)
                .checkedAt(checkedAt)
                .responseMs(responseMs)
                .httpStatus(httpStatus)
                .isUp(isUp)
                .region(region)
                .errorMessage(errorMessage.orElse(null))
                .build();

        this.checkRepository.save(check);
    }

    public void processCheckResult(UUID monitorId, boolean isUp, int threshold) {

        if (!isUp) {

            Long failures = cachePort.increment("failures:" + monitorId);
            cachePort.expire("failures:" + monitorId, 1L, TimeUnit.DAYS);

            if (failures == 1) {
                monitorProducerPort.sendMonitorUpdate(new MonitorUpdate(monitorId, "DEGRADED"));
            }

            if (failures >= threshold) {

                log.info("Threshold exceeded for monitor={}, failures={}", monitorId, failures);

                boolean incidentAlreadyOpen = incidentRepository
                        .existsByMonitorIdAndStatus(monitorId, IncidentStatus.OPEN);

                if (!incidentAlreadyOpen) {
                    openIncident(monitorId);
                    monitorProducerPort.sendMonitorUpdate(new MonitorUpdate(monitorId, "INCIDENT"));
                }
            }

        } else {

            Long previousFailures = this.cachePort.delete("failures:" + monitorId);

            if (previousFailures != null && previousFailures > 0) {
                closeIncident(monitorId);
                monitorProducerPort.sendMonitorUpdate(new MonitorUpdate(monitorId, "ACTIVE"));
            }
        }
    }

    private void openIncident(UUID monitorId) {
        var incident = Incident.open(monitorId);

        this.incidentRepository.save(incident);

        this.alertProducerPort.sendAlert(
                this.alertMapper.toAlert(incident)
        );
    }

    private void closeIncident(UUID monitorId) {
        var incident = this.incidentRepository.findByMonitorIdOpen(monitorId);

        if (incident.isEmpty()) {
            return;
        }

        incident.get().resolve();
        this.incidentRepository.save(incident.get());

        this.alertProducerPort.sendAlert(
                this.alertMapper.toAlert(incident.get())
        );
    }
}
