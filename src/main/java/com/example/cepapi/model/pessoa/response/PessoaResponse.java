package com.example.cepapi.model.pessoa.response;

import com.example.cepapi.model.cep.CepEntity;
import com.example.cepapi.model.payment.DebitCard;
import com.example.cepapi.model.weather.WeatherEntity;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class PessoaResponse {
    private String id;
    private String nome;
    private String dataDeNascimento;
    private CepEntity endereco;
    private WeatherEntity temperatura;
    private DebitCard.Builder debitCard;

    public CepEntity getEndereco() {
        return endereco;
    }

    public WeatherEntity getTemperatura() {
        return temperatura;
    }

    public DebitCard.Builder getDebitCard() {
        return debitCard;
    }
}
