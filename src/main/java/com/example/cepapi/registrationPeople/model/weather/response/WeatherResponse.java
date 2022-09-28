package com.example.cepapi.registrationPeople.model.weather.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {
    //ResponseBody
    @JsonProperty("temp")
    private String temp;
    @JsonProperty("feels_like")
    private String feelsLike;
    @JsonProperty("min_temp")
    private String minTemp;
    @JsonProperty("max_temp")
    private String maxTemp;

}
