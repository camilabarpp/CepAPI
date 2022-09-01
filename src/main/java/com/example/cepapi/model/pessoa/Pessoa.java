package com.example.cepapi.model.pessoa;

import com.example.cepapi.model.cep.CepEntity;
import com.example.cepapi.model.weather.WeatherEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Pessoa {

    @Id
    private String id;
    private String nome;
    private String dataDeNascimento;
    private CepEntity endereco;
    private WeatherEntity temperatura;

    public CepEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(CepEntity endereco) {
        this.endereco = endereco;
    }

    public WeatherEntity getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(WeatherEntity temperatura) {
        this.temperatura = temperatura;
    }
}


