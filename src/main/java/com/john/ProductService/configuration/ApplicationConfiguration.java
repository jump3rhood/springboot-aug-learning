package com.john.ProductService.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    // Create a method that will provide a RestTemplate
    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }
}
