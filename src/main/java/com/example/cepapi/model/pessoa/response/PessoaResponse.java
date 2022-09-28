package com.example.cepapi.model.pessoa.response;

import com.example.cepapi.model.cep.CepEntity;
import com.example.cepapi.model.payment.CreditCard;
import com.example.cepapi.model.payment.DebitCard;
import com.example.cepapi.model.payment.PayPal;
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

    public CepEntity getEndereco() {
        return endereco;
    }

    public WeatherEntity getTemperatura() {
        return temperatura;
    }


}
