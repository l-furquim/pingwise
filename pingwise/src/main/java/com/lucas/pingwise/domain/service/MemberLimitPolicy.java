package com.lucas.pingwise.domain.service;

import com.lucas.pingwise.domain.exception.PlanLimitException;
import org.springframework.stereotype.Component;

@Component
public class MemberLimitPolicy {

    public void validate(long currentMemberCount, long planMemberLimit) {
        if (currentMemberCount >= planMemberLimit) {
            throw new PlanLimitException("Member limit exceeded");
        }
    }

}
