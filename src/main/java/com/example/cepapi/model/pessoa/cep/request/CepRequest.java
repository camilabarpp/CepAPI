package com.example.cepapi.model.pessoa.cep.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CepRequest {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
