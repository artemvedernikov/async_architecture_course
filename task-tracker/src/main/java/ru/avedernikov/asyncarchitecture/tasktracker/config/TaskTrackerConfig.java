package ru.avedernikov.asyncarchitecture.tasktracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TaskTrackerConfig {
    // idk where to put it
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
