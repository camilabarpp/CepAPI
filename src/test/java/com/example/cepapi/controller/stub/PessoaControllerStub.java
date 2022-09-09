package com.example.cepapi.controller.stub;

import com.example.cepapi.model.cep.CepEntity;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.model.weather.WeatherEntity;
import com.example.cepapi.model.weather.response.WeatherResponse;

public class PessoaControllerStub {

    public static Pessoa createAEntity() {
        return Pessoa.builder()
                .id("1")
                .nome("Camila")
                .dataDeNascimento("02/07/1996")
                .endereco(createAEntityCep())
                .temperatura(createAEntityWeather())
                .build();
    }

    public static Pessoa createAEntityNull() {
        return Pessoa.builder()
                .id(null)
                .build();
    }

    public static CepEntity createAEntityCep() {
        return CepEntity.builder()
                .cep("94020070")
                .logradouro("Rua Joao Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build();
    }

    public static WeatherEntity createAEntityWeather() {
        return WeatherEntity.builder()
                .temp("20")
                .feelsLike("20")
                .minTemp("20")
                .maxTemp("20")
                .build();
    }

    public static WeatherResponse createAResponseWeather() {
        return WeatherResponse.builder()
                .temp("20")
                .feelsLike("20")
                .minTemp("20")
                .maxTemp("20")
                .build();
    }

    public static PessoaRequest createARequest() {
        return PessoaRequest.builder()
                .id("1")
                .nome("Camila")
                .dataDeNascimento("02/07/1996")
                .endereco(createAEntityCep())
                .temperatura(createAEntityWeather())
                .build();
    }

    public static PessoaResponse createAResponse() {
        return PessoaResponse.builder()
                .id("1")
                .nome("Camila")
                .dataDeNascimento("02/07/1996")
                .endereco(createAEntityCep())
                .temperatura(createAEntityWeather())
                .build();
    }
}