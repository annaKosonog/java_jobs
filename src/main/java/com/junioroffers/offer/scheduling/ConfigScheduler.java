package com.junioroffers.offer.scheduling;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
@PropertySource("classpath:application.properties")
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class ConfigScheduler {
}
