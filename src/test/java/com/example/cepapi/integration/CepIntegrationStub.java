package com.example.cepapi.integration;

import com.example.cepapi.model.cep.response.CepResponse;

public class CepIntegrationStub {

    public static CepResponse cepIntegrationResponseExpect() {
        return CepResponse.builder()
                .cep("94020070")
                .logradouro("Rua João Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build();
    }

    public static CepResponse cepIntegrationResponse() {
        return CepResponse.builder()
                .cep("94020070")
                .logradouro("Rua João Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build();
    }



}
