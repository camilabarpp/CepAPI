package com.example.cepapi.repository;

import com.example.cepapi.model.weather.WeatherEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.cepapi.controller.stub.PessoaControllerStub.createAEntityWeather;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataMongoTest //se concentra somente nos componentes do mongo
@EnableMongoRepositories //para ativar os repositories do mongo
class WeatherRepositoryTest {
    @SpyBean //é usada para aplicar espiões do Mockito ao ApplicationContext sem substituir o bean existente
    private MongoTemplate mongoTemplate;
    @Autowired
    private WeatherRepository weatherRepository;

    @Test
    @DisplayName("Deve salvar uma pessoa no banco de dados")
    void shouldSaveAPerson() {

        var weather = createAEntityWeather();
        mongoTemplate.save(weather);

        var weatherSaved = weatherRepository.save(weather);

        assertThat(weatherSaved.getTemp()).isNotNull();

        verify(mongoTemplate).save(weather);

    }

    @Test
    @DisplayName("Deve alterar uma pessoa no banco de dados")
    void shouldUpdateAPerson() {

        var weather = createAEntityWeather();
        mongoTemplate.save(weather);

        var weatherUpdate = new WeatherEntity();

        weatherUpdate.setTemp(weather.getTemp());
        weatherUpdate.setFeelsLike(weather.getFeelsLike());
        weatherUpdate.setMinTemp(weather.getMinTemp());
        weatherUpdate.setMaxTemp(weather.getMaxTemp());

        var atualizada = weatherRepository.save(weather);

        assertThat(atualizada.getTemp()).isNotNull();
        assertEquals(atualizada.getTemp(), weatherUpdate.getTemp());
        assertEquals(atualizada.getFeelsLike(), weatherUpdate.getFeelsLike());
        assertEquals(atualizada.getMinTemp(), weatherUpdate.getMinTemp());
        assertEquals(atualizada.getMaxTemp(), weatherUpdate.getMaxTemp());

        verify(mongoTemplate).save(weather);

    }
}
