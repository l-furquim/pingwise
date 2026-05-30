package com.lucas.pingwise_worker.application.usecases;

import com.lucas.pingwise_worker.application.mappers.AlertMapper;
import com.lucas.pingwise_worker.application.ports.in.dto.HandleCheckJobCommand;
import com.lucas.pingwise_worker.application.ports.out.*;
import com.lucas.pingwise_worker.infrastructure.http.TimingInterceptor;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.UUID;

class HandleCheckJobUseCaseImplTest {

    @Mock
    private AlertMapper alertMapper;

    @Mock
    private TimingInterceptor timingInterceptor;

    @Mock
    private RestClient restClient;

    @Mock
    private CheckRepository checkRepository;

    @Mock
    private IncidentRepository incidentRepository;

    @Mock
    private CachePort cachePort;

    @Mock
    private MonitorProducerPort monitorProducerPort;

    @Mock
    private AlertProducerPort alertProducerPort;

    @Test
    void should_create_an_incident_when_reached_threshold() {
        UUID incidentId = UUID.randomUUID();
        UUID monitorId = UUID.randomUUID();
        UUID tenantId = UUID.randomUUID();

        HandleCheckJobCommand command = new HandleCheckJobCommand(
                monitorId,
                tenantId,
                "Google",
                "https://google.com",
                10,
                2
        );

        ResponseEntity<Object> response = ResponseEntity.internalServerError().build();

        Mockito.when(restClient.get()).thenReturn(ResponseEntity.internalServerError().build())
                .assert


    }


}