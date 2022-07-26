package com.example.cepapi.registrationPeople.model.pessoa.request;

import com.example.cepapi.cep.model.CepEntity;
import com.example.cepapi.cafe.model.payment.CreditCard;
import com.example.cepapi.cafe.model.payment.DebitCard;
import com.example.cepapi.cafe.model.payment.PayPal;
import com.example.cepapi.registrationPeople.model.weather.WeatherEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaRequest {
    private String id;
    @NotBlank(message = "{nome.not.blank}")
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotBlank(message = "{dataDeNascimento.not.blank}")
    private String dataDeNascimento;
    private CepEntity endereco;
    private WeatherEntity temperatura;
    private DebitCard.Builder debitCard;
    private CreditCard.Builder creditCard;
    private PayPal.builder paypal;

    public CepEntity getEndereco() {
        return endereco;
    }

    public DebitCard.Builder getDebitCard() {
        return debitCard;
    }
}

