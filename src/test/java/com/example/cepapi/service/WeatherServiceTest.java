package com.example.cepapi.service;

import com.example.cepapi.configuration.resttemplate.weather.IntegrationWeather;
import com.example.cepapi.registrationPeople.model.pessoa.Pessoa;
import com.example.cepapi.registrationPeople.model.weather.WeatherEntity;
import com.example.cepapi.registrationPeople.model.weather.response.WeatherResponse;
import com.example.cepapi.registrationPeople.repository.WeatherRepository;
import com.example.cepapi.registrationPeople.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.cepapi.controller.stub.PessoaControllerStub.*;
import static com.example.cepapi.registrationPeople.model.weather.mapper.WeatherMapper.toEntity;
import static com.example.cepapi.registrationPeople.model.weather.mapper.WeatherMapper.toResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@RequiredArgsConstructor
class WeatherServiceTest {
    @Mock
    private WeatherRepository repository;
    @InjectMocks
    private WeatherService service;
    @Mock
    private IntegrationWeather integrationWeather;

    @Test
    @DisplayName("Pesquisar temperatura e salvar no banco de dados")
    void pesquisarTemperaturaESalvarNoBanco() {
        Pessoa pessoa = createAEntity();
        String city = pessoa.getEndereco().getLocalidade();
        WeatherResponse response = createAResponseWeather();
        WeatherEntity entity = createAEntityWeather();

        when(integrationWeather.getWeather(city)).thenReturn(toResponse(entity));
        when(repository.save(entity)).thenReturn(toEntity(response));

        service.pesquisarTemperaturaESalvarNoBanco(pessoa);
        pessoa.setTemperatura(entity);

        assertNotNull(entity);
        assertEquals(pessoa.getTemperatura().getTemp(), entity.getTemp());
        assertEquals(pessoa.getTemperatura().getFeelsLike(), entity.getFeelsLike());
        assertEquals(pessoa.getTemperatura().getMinTemp(), entity.getMinTemp());
        assertEquals(pessoa.getTemperatura().getMaxTemp(), entity.getMaxTemp());
    }

    @Test
    @DisplayName("Deve pesquisar cep da camada SERVICE")
    void shouldFindCep() {
        WeatherResponse response = new WeatherResponse();
        when(integrationWeather.getWeather("Canoas"))
                .thenReturn(response);
        var actual = service.getWeather("Canoas");
        assertNotNull(actual);
    }
}