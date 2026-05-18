package com.lucas.pingwise_worker.infrastructure.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient(TimingInterceptor timingInterceptor) {
        return RestClient.builder()
                .requestInterceptor(timingInterceptor)
                .build();
    }

}
