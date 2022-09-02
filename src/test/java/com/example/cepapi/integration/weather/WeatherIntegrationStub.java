package com.example.cepapi.integration.weather;

import com.example.cepapi.model.weather.response.WeatherResponse;

public class WeatherIntegrationStub {

    public static WeatherResponse  weatherIntegrationResponseExpect() {
        return WeatherResponse.builder()
                .temp("16")
                .feelsLike("16")
                .minTemp("16")
                .maxTemp("16")
                .build();
    }

    public static WeatherResponse weatherIntegrationResponse() {
        return WeatherResponse.builder()
                .temp("16")
                .feelsLike("16")
                .minTemp("16")
                .maxTemp("16")
                .build();
    }



}
