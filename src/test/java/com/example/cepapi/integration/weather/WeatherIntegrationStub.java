package com.example.cepapi.integration.weather;

import com.example.cepapi.registrationPeople.model.weather.response.WeatherResponse;

public class WeatherIntegrationStub {

    public static WeatherResponse  weatherIntegrationResponseExpect() {
        return WeatherResponse.builder()
                .temp("18")
                .feelsLike("18")
                .minTemp("18")
                .maxTemp("21")
                .build();
    }

    public static WeatherResponse weatherIntegrationResponse() {
        return WeatherResponse.builder()
                .temp("18")
                .feelsLike("18")
                .minTemp("18")
                .maxTemp("21")
                .build();
    }



}
