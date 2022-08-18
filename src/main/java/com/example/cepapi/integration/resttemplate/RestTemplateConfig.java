package com.example.cepapi.integration.resttemplate;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().rootUri("https://viacep.com.br/ws/{cep}/json")
                //.defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                //.errorHandler(new ErroHandler())
                .build();
    }
}
