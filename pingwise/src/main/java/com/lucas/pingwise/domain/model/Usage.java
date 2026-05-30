package com.lucas.pingwise.domain.model;


import java.time.LocalDateTime;

public record Usage (
        Integer monitorsUsed,
        Long membersUsed,
        Long apiCallsToday,
        LocalDateTime apiCallsResetsAt
){
    public static Usage build() {
        return new Usage(
                0,
                0L,
                0L,
                null
        );
    }
}
