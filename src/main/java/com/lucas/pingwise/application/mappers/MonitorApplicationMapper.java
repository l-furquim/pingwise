package com.lucas.pingwise.application.mappers;

import com.lucas.pingwise.adapters.in.rest.monitor.dto.CreateMonitorRequest;
import com.lucas.pingwise.adapters.in.rest.monitor.dto.MonitorResponse;
import com.lucas.pingwise.application.ports.in.monitor.dto.CreateMonitorCommand;
import com.lucas.pingwise.domain.model.Monitor;
import com.lucas.pingwise.domain.model.Plan;
import com.lucas.pingwise.domain.model.Tenant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MonitorApplicationMapper {

   private final TenantApplicationMapper tenantApplicationMapper;


   public CreateMonitorCommand toCommand(CreateMonitorRequest request) {
       return new CreateMonitorCommand(
               request.name(),
               request.url(),
               request.intervalSeconds(),
               request.timeoutMs(),
               request.consecutiveFailuresThreshold(),
               request.allowPublicAccess()
       );
   }

   public MonitorResponse toResponse(Monitor monitor, Tenant tenant, Plan plan) {
      return new MonitorResponse(
              monitor.getName(),
              monitor.getUrl(),
              monitor.getIntervalSeconds(),
              monitor.getTimeoutMs(),
              monitor.getConsecutiveFailuresThreshold(),
              monitor.getStatus().getValue(),
              monitor.isPublic(),
              monitor.daysMonitoring(),
              monitor.getCreatedAt(),
              this.tenantApplicationMapper.toResponse(
                    tenant,
                    plan
              )
      );
   }

}
