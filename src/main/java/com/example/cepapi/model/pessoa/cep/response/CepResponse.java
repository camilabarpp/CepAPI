package com.example.cepapi.model.pessoa.cep.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CepResponse {
    @NotNull
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
