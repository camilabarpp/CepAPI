package com.example.cepapi.model.weather.mapper;

import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.weather.WeatherEntity;
import com.example.cepapi.model.weather.request.WeatherRequest;
import com.example.cepapi.model.weather.response.WeatherResponse;
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

    public static WeatherRequest pessoaToWeatherRequest(Pessoa pessoa){
        return WeatherRequest.builder()
                .temp(pessoa.getTemperatura().getTemp())
                .feelsLike(pessoa.getTemperatura().getFeelsLike())
                .minTemp(pessoa.getTemperatura().getMinTemp())
                .maxTemp(pessoa.getTemperatura().getMaxTemp())
                .build();
    }

    //request to entity
    public WeatherEntity requestToEntity(WeatherRequest weather) {
        return WeatherEntity.builder()
                .temp(weather.getTemp())
                .feelsLike(weather.getFeelsLike())
                .minTemp(weather.getMinTemp())
                .maxTemp(weather.getMaxTemp())
                .build();
    }




    //entity to request
    public static WeatherRequest entityToRequest(WeatherEntity weatherEntity){
        return WeatherRequest.builder()
                .temp(weatherEntity.getTemp())
                .feelsLike(weatherEntity.getFeelsLike())
                .minTemp(weatherEntity.getMinTemp())
                .maxTemp(weatherEntity.getMaxTemp())
                .build();
    }
}
