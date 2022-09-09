package com.example.cepapi.integration.resttemplate.weather;

import com.example.cepapi.model.weather.response.WeatherResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@NoArgsConstructor
public class IntegrationWeather {

    private RestTemplate restTemplate;
    @Autowired
    public IntegrationWeather(@Qualifier("restTemplateWeather") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public WeatherResponse getWeather(String city) {
        return restTemplate.getForObject("/v1/weather?city=".concat(city), WeatherResponse.class);
    }
}
