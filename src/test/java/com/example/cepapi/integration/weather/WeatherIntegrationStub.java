package com.example.cepapi.integration.weather;

import com.example.cepapi.model.weather.response.WeatherResponse;

public class WeatherIntegrationStub {

    public static WeatherResponse  weatherIntegrationResponseExpect() {
        return WeatherResponse.builder()
                .temp("20")
                .feelsLike("20")
                .minTemp("20")
                .maxTemp("22")
                .build();
    }

    public static WeatherResponse weatherIntegrationResponse() {
        return WeatherResponse.builder()
                .temp("20")
                .feelsLike("20")
                .minTemp("20")
                .maxTemp("22")
                .build();
    }



}
