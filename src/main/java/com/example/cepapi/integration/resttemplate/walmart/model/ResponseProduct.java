package com.example.cepapi.integration.resttemplate.walmart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseProduct {
    @NotNull
    public String cep;
    public String logradouro;
    public String bairro;
    public String localidade;
    public String uf;
}
