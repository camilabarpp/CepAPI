package com.example.cepapi.registrationPeople.model.pessoa.response;

import com.example.cepapi.cafe.model.payment.CreditCard;
import com.example.cepapi.cafe.model.payment.DebitCard;
import com.example.cepapi.cafe.model.payment.PayPal;
import com.example.cepapi.cep.model.CepEntity;
import com.example.cepapi.registrationPeople.model.weather.WeatherEntity;
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
