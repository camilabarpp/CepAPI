package com.example.cepapi.service;

import com.example.cepapi.integration.resttemplate.weather.IntegrationWeather;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.weather.WeatherEntity;
import com.example.cepapi.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.cepapi.controller.PessoaControllerStub.createAEntity;
import static com.example.cepapi.controller.PessoaControllerStub.createAEntityWeather;
import static com.example.cepapi.model.weather.mapper.WeatherMapper.toResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@RequiredArgsConstructor
class WeatherServiceTest {
    @Mock
    private WeatherRepository repository;
    @Mock
    private WeatherService service;
    @Mock
    private IntegrationWeather integrationWeather;

    @Test
    void pesquisarTemperaturaESalvarNoBanco() {
        String city = "Canoas";
        Pessoa pessoa = createAEntity();
        WeatherEntity entity = createAEntityWeather();

        //repository.save(entity);

        when(integrationWeather.getWeather(city)).thenReturn(toResponse(entity));
        when(repository.save(entity)).thenReturn(entity);

        service.pesquisarTemperaturaESalvarNoBanco(pessoa);

        assertEquals(pessoa.getTemperatura().getTemp(), entity.getTemp());
        assertEquals(pessoa.getTemperatura().getFeelsLike(), entity.getFeelsLike());
        assertEquals(pessoa.getTemperatura().getMinTemp(), entity.getMinTemp());
        assertEquals(pessoa.getTemperatura().getMaxTemp(), entity.getMaxTemp());
    }
}