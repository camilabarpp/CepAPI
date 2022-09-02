package com.example.cepapi.integration.weather;

import com.example.cepapi.model.weather.response.WeatherResponse;

public class WeatherIntegrationStub {

    public static WeatherResponse  weatherIntegrationResponseExpect() {
        return WeatherResponse.builder()
                .temp("17")
                .feelsLike("17")
                .minTemp("16")
                .maxTemp("20")
                .build();
    }

    public static WeatherResponse weatherIntegrationResponse() {
        return WeatherResponse.builder()
                .temp("17")
                .feelsLike("17")
                .minTemp("16")
                .maxTemp("20")
                .build();
    }



}
