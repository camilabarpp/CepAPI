package com.example.cepapi.integration.weather;

import com.example.cepapi.model.weather.response.WeatherResponse;

public class WeatherIntegrationStub {

    public static WeatherResponse  weatherIntegrationResponseExpect() {
        return WeatherResponse.builder()
                .temp("27")
                .feelsLike("28")
                .minTemp("26")
                .maxTemp("29")
                .build();
    }

    public static WeatherResponse weatherIntegrationResponse() {
        return WeatherResponse.builder()
                .temp("27")
                .feelsLike("28")
                .minTemp("26")
                .maxTemp("29")
                .build();
    }



}
