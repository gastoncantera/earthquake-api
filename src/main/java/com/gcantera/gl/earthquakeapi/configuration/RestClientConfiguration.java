package com.gcantera.gl.earthquakeapi.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestClientConfiguration {

    static final int SOCKET_TIMEOUT = 120000;
    static final int CONNECTION_TIMEOUT = 5000;

    @Bean
    public RestTemplate restClient() {
        return new RestTemplateBuilder()
                .setReadTimeout(Duration.ofMillis(SOCKET_TIMEOUT))
                .setConnectTimeout(Duration.ofMillis(CONNECTION_TIMEOUT))
                .build();
    }
}
