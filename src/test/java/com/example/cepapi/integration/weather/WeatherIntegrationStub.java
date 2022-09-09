package com.example.cepapi.integration.weather;

import com.example.cepapi.model.weather.response.WeatherResponse;

public class WeatherIntegrationStub {

    public static WeatherResponse  weatherIntegrationResponseExpect() {
        return WeatherResponse.builder()
                .temp("29")
                .feelsLike("31")
                .minTemp("28")
                .maxTemp("30")
                .build();
    }

    public static WeatherResponse weatherIntegrationResponse() {
        return WeatherResponse.builder()
                .temp("29")
                .feelsLike("31")
                .minTemp("28")
                .maxTemp("30")
                .build();
    }



}
