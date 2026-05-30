package com.lucas.pingwise.application.mappers;

import com.lucas.pingwise.adapters.in.rest.tenant.dto.CheckResponse;
import com.lucas.pingwise.domain.model.Check;
import org.springframework.stereotype.Component;

@Component
public class CheckApplicationMapper {

    public CheckResponse toResponse(Check check) {
        return new CheckResponse(
                check.getId(),
                check.getCheckedAt(),
                check.getResponseMs(),
                check.getHttpStatus(),
                check.isUp(),
                check.getRegion(),
                check.getErrorMessage()
        );
    }

}
