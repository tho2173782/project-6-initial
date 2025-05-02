package com.example.aggregator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AggregatorConfig {

    // This class is used to configure the RestTemplate bean
    // and any other beans that are needed for the aggregator service.
    // It is currently empty, but can be used to add any configuration
    // that is needed in the future.

    @Bean
    public RestTemplate getTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // You can customize the RestTemplate here if needed
        // For example, you can add interceptors, message converters, etc.
        // Example: restTemplate.setInterceptors(...);

        // Create and return a new RestTemplate instance
        return restTemplate;
    }
}
