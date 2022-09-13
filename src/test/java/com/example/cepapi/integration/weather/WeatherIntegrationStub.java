package com.example.cepapi.integration.weather;

import com.example.cepapi.model.weather.response.WeatherResponse;

public class WeatherIntegrationStub {

    public static WeatherResponse  weatherIntegrationResponseExpect() {
        return WeatherResponse.builder()
                .temp("19")
                .feelsLike("19")
                .minTemp("18")
                .maxTemp("19")
                .build();
    }

    public static WeatherResponse weatherIntegrationResponse() {
        return WeatherResponse.builder()
                .temp("19")
                .feelsLike("19")
                .minTemp("18")
                .maxTemp("19")
                .build();
    }



}
