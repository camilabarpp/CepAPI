package com.example.cepapi.registrationPeople.model.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WeatherEntity {
    private String temp;
    private String feelsLike;
    private String minTemp;
    private String maxTemp;

}
