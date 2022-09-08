package com.example.cepapi.model.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class WeatherEntity {

    private String temp;
    private String feelsLike;
    private String minTemp;
    private String maxTemp;

}
