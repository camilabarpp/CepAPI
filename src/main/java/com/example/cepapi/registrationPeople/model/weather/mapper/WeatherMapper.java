package com.example.cepapi.registrationPeople.model.weather.mapper;

import com.example.cepapi.registrationPeople.model.weather.WeatherEntity;
import com.example.cepapi.registrationPeople.model.weather.response.WeatherResponse;
import lombok.experimental.UtilityClass;
@UtilityClass
public class WeatherMapper {
    //entity to response
    public static WeatherResponse toResponse(WeatherEntity weatherEntity){
        return WeatherResponse.builder()
                .temp(weatherEntity.getTemp())
                .feelsLike(weatherEntity.getFeelsLike())
                .minTemp(weatherEntity.getMinTemp())
                .maxTemp(weatherEntity.getMaxTemp())
                .build();
    }

    //response to entity
    public static WeatherEntity toEntity(WeatherResponse weatherResponse){
        return WeatherEntity.builder()
                .temp(weatherResponse.getTemp())
                .feelsLike(weatherResponse.getFeelsLike())
                .minTemp(weatherResponse.getMinTemp())
                .maxTemp(weatherResponse.getMaxTemp())
                .build();
    }
}
