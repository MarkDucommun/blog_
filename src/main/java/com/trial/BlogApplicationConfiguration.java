package com.trial;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class BlogApplicationConfiguration {
    @Bean
    Clock provideClock() {
        return Clock.systemUTC();
    }
}
