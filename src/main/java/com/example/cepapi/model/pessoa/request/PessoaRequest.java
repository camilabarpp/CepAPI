package com.example.cepapi.model.pessoa.request;

import com.example.cepapi.model.cep.CepEntity;
import com.example.cepapi.model.payment.DebitCard;
import com.example.cepapi.model.weather.WeatherEntity;
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

    public CepEntity getEndereco() {
        return endereco;
    }

    public DebitCard.Builder getDebitCard() {
        return debitCard;
    }
}

