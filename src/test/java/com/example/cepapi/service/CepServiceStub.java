package com.example.cepapi.service;

import com.example.cepapi.integration.resttemplate.walmart.model.DataIntegrationResponse;
import com.example.cepapi.integration.resttemplate.walmart.model.ResponseData;
import com.example.cepapi.integration.resttemplate.walmart.model.ResponseProduct;
import com.example.cepapi.model.pessoa.cep.CepEntity;
import com.example.cepapi.model.pessoa.cep.response.CepResponse;

import java.util.Optional;

public class CepServiceStub {
    static DataIntegrationResponse CepIntegrationStubResponse(){
        return DataIntegrationResponse.builder()
                .data(ResponseData.builder()
                        .product(ResponseProduct.builder()
                                .cep("94020070")
                                .logradouro("Rua Joao Dutra")
                                .bairro("Salgado Filho")
                                .localidade("Gravataí")
                                .uf("RS")
                                .build())
                        .build())
                .build();
    }
/*
    static DataIntegrationResponse WalmartServiceStubBadRequest(){
        return DataIntegrationResponse.builder()
                .data(ResponseData.builder()
                        .product(ResponseProduct.builder()
                                .usItemId(null)
                                .id(null)
                                .segment(null)
                                .type(null)
                                .name(null)
                                .description(null)
                                .build())
                        .build())
                .build();
    }*/

    static CepEntity cepEntityStub(){
        return  CepEntity.builder()
                .cep("94020070")
                .logradouro("Rua Joao Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build();
    }


    static Optional<CepEntity> CepServiceStubExpected(){
        return Optional.ofNullable(CepEntity.builder()
                .cep("94020070")
                .logradouro("Rua Joao Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build());
    }

    static Optional<CepEntity> CepServiceStubResponse(){
        return  Optional.ofNullable(CepEntity.builder()
                .cep("94020070")
                .logradouro("Rua Joao Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build());
    }

    static CepResponse CepServiceResponseExpectedStub(){
        return CepResponse.builder()
                .cep("94020070")
                .logradouro("Rua Joao Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build();
    }
}
}
