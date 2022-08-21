package com.example.cepapi.service;

import com.example.cepapi.integration.resttemplate.walmart.model.DataIntegrationResponse;
import com.example.cepapi.integration.resttemplate.walmart.model.ResponseData;
import com.example.cepapi.integration.resttemplate.walmart.model.ResponseProduct;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.cep.CepEntity;
import com.example.cepapi.model.pessoa.cep.response.CepResponse;
import com.example.cepapi.model.pessoa.response.PessoaResponse;

import java.util.Optional;

public class CepServiceStub {
    static DataIntegrationResponse CepIntegrationStubResponse(){
        return DataIntegrationResponse.builder()
                .data(ResponseData.builder()
                        .product(ResponseProduct.builder()
                                .cep("94020-070")
                                .logradouro("Rua João Dutra")
                                .bairro("Salgado Filho")
                                .localidade("Gravataí")
                                .uf("RS")
                                .build())
                        .build())
                .build();
    }
    static DataIntegrationResponse CepServiceStubBadRequest(){
        return DataIntegrationResponse.builder()
                .data(ResponseData.builder()
                        .product(ResponseProduct.builder()
                                .cep(null)
                                .logradouro(null)
                                .bairro(null)
                                .localidade(null)
                                .uf(null)
                                .build())
                        .build())
                .build();
    }

    static CepEntity cepEntityStub(){
        return CepEntity.builder()
                .cep("94020-070")
                .logradouro("Rua João Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build();
    }


    static Optional<CepEntity> CepServiceStubExpected(){
        return Optional.ofNullable(CepEntity.builder()
                .cep("94020-070")
                .logradouro("Rua João Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build());
    }

    static Optional<CepEntity> CepServiceStubResponse(){
        return  Optional.ofNullable(CepEntity.builder()
                .cep("94020-070")
                .logradouro("Rua João Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build());
    }

    static CepResponse CepServiceResponseExpectedStub(){
        return CepResponse.builder()
                .cep("94020-070")
                .logradouro("Rua João Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build();
    }
}

