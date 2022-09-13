package com.example.cepapi.controller;

import com.example.cepapi.configuration.exception.ApiNotFoundException;
import com.example.cepapi.model.weather.WeatherEntity;
import com.example.cepapi.model.weather.mapper.WeatherMapper;
import com.example.cepapi.service.WeatherService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.cepapi.controller.stub.PessoaControllerStub.createAEntityWeather;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class WeatherControllerTest {
    @InjectMocks
    private WeatherController controller;
    @Mock
    private WeatherService service;

    @Test
    @DisplayName("Deve procurar temperatura com sucesso")
    void shouldFindCityWithSuccess() {
        String city = "Canoas";
        WeatherEntity entity = createAEntityWeather();

        when(service.getWeather(city)).thenReturn((WeatherMapper.toResponse(entity)));

        var response = controller.getCity(city);

        assertNotNull(response);
        assertEquals(entity.getTemp(), response.getTemp());
        assertEquals(entity.getFeelsLike(), response.getFeelsLike());
        assertEquals(entity.getMaxTemp(), response.getMaxTemp());
        assertEquals(entity.getMinTemp(), response.getMinTemp());
    }

    @Test
    @DisplayName("Deve lançar ApiNotFoundException quando o nome da cidade estiver incorreto")
    void shouldNotFindCityAndThrowsApiNotFoundException() {
        when(service.getWeather("94")).thenThrow(ApiNotFoundException.class);

        assertThrows(ApiNotFoundException.class, () -> controller.getCity("94"));
    }
}
