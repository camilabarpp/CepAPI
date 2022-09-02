package com.example.cepapi.model.pessoa;

import com.example.cepapi.model.cep.CepEntity;
import com.example.cepapi.model.weather.WeatherEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class Pessoa {

    @Id
    @ApiModelProperty(notes = "id of the user")
    private String id;
    @ApiModelProperty(notes = "nome of the user")
    private String nome;
    @ApiModelProperty(notes = "Birthdate of the user")
    private String dataDeNascimento;
    @ApiModelProperty(notes = "Address of the user")
    private CepEntity endereco;
    @ApiModelProperty(notes = "Weather of the city")
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


