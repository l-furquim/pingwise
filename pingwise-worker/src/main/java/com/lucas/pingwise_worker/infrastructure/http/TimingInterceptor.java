package com.lucas.pingwise_worker.infrastructure.http;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class TimingInterceptor implements ClientHttpRequestInterceptor {

    @Getter
    private long responseMs;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        int start = (int) System.currentTimeMillis();
        try {
            return execution.execute(request, body);
        } finally {
            long duration = System.currentTimeMillis() - start;
            log.info("{} {} took {} ms", request.getMethod(), request.getURI(), duration);

            this.responseMs = duration;
        }
    }
}
