package com.junioroffers.offer.scheduling;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
@PropertySource("classpath:application-scheduled.properties")
public class ConfigScheduler {
}
