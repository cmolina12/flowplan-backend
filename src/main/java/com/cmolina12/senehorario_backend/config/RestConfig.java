package com.cmolina12.senehorario_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean // The purpose of this bean is to create a RestTemplate instance that can be used to make HTTP requests, the idea is to use it in the tests to mock the responses from the external API.
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
