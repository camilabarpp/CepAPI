package com.example.cepapi.integration.resttemplate.weather;

import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.model.weather.response.WeatherResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Component
public class IntegrationWeather {
    @Qualifier("restTemplateWeather")
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        return restTemplate.getForObject("/v1/weather?city=".concat(city), WeatherResponse.class);
    }
}
