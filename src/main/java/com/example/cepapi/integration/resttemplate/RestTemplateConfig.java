package com.example.cepapi.integration.resttemplate;

import com.example.cepapi.configuration.exception.ErroHandler;
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
        return new RestTemplateBuilder()
                .rootUri("https://viacep.com.br")
                .errorHandler(new ErroHandler())
                .build();
    }
}
