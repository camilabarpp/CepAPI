package com.example.cepapi.configuration.resttemplate;

import com.example.cepapi.configuration.exception.ErroHandler;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@Configuration
public class RestTemplateConfig {

    @Bean //@Primary
    public RestTemplate restTemplateCep() {
        return new RestTemplateBuilder()
                .rootUri("https://viacep.com.br")
                .errorHandler(new ErroHandler())
                .build();
    }

    @Bean
    public RestTemplate restTemplateWeather() {
        return new RestTemplateBuilder()
                .rootUri("https://weather-by-api-ninjas.p.rapidapi.com")
                .defaultHeader("X-RapidAPI-Key", "3c2316e5ccmsh4c9334453d4100cp11f529jsn398286bc960e")
                .defaultHeader("X-RapidAPI-Host", "weather-by-api-ninjas.p.rapidapi.com")
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .errorHandler(new ErroHandler())
                .build();
    }
}
