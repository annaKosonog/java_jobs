package com.junioroffers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class OfferClientConfig {

    @Bean
    public RestTemplate restTemplate(@Value("${offer.client.config.url}") String url, @Value("${offer.client.config.connectTimeout}") long connectTimeout,
                                     @Value("${offer.client.config.readTimeout}") long readTimeout) {
        return new RestTemplateBuilder()
                .rootUri(url)
                .setConnectTimeout(Duration.ofMillis(connectTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }
}

