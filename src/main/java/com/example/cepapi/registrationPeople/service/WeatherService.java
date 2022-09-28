package com.example.cepapi.registrationPeople.service;

import com.example.cepapi.configuration.resttemplate.weather.IntegrationWeather;
import com.example.cepapi.registrationPeople.model.pessoa.Pessoa;
import com.example.cepapi.registrationPeople.model.weather.response.WeatherResponse;
import com.example.cepapi.registrationPeople.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.cepapi.registrationPeople.model.weather.mapper.WeatherMapper.toEntity;


@Service
@AllArgsConstructor
public class WeatherService {

    private WeatherRepository repository;
    private IntegrationWeather integrationWeather;

    public void pesquisarTemperaturaESalvarNoBanco(Pessoa pessoa) {
        String city = pessoa.getEndereco().getLocalidade();
        var temperatura = repository.findById((city)).orElseGet(() -> {
            var novaTemperatura = toEntity(this.integrationWeather.getWeather(city));
            repository.save(novaTemperatura);
            return novaTemperatura;
        });
        pessoa.setTemperatura(temperatura);
    }

    public WeatherResponse getWeather(String city) {
        return integrationWeather.getWeather(city);
    }
}
