package com.example.productservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    private static RestTemplate restTemplate;

    @Bean
    public static RestTemplate getRestTemplate(){
        if (restTemplate == null){
            restTemplate = new RestTemplate();
        }

        return restTemplate;
    }
}
