package com.example.cepapi.controller;

import com.example.cepapi.model.weather.response.WeatherResponse;
import com.example.cepapi.service.WeatherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("v1/api/temp")
@AllArgsConstructor
public class WeatherController {

    private WeatherService service;

    @GetMapping("/{city}")
    @ApiOperation("Get city weather")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Get city weather"),
            @ApiResponse(code = 404, message = "Schema not found"),
            @ApiResponse(code = 400, message = "Missing or invalid request body"),
            @ApiResponse(code = 500, message = "Internal error")})
    public WeatherResponse getCep(@PathVariable String city) {
        log.info("Mostrando temperatura de " + city);
        return service.getWeather(city);
    }
}
