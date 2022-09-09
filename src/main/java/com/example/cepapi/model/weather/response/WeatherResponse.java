package com.example.cepapi.model.weather.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {
    //ResponseBody
    private String temp;
    @JsonProperty("feels_like")
    private String feelsLike;
    @JsonProperty("min_temp")
    private String minTemp;
    private String maxTemp;

}
