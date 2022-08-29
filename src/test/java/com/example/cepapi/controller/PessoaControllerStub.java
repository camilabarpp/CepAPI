package com.example.cepapi.controller;

import com.example.cepapi.model.cep.CepEntity;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;

public class PessoaControllerStub {

    static Pessoa createAEntity() {
        return Pessoa.builder()
                .id("1")
                .nome("Camila")
                .dataDeNascimento("02/07/1996")
                .endereco(createAEntityCep())
                .build();
    }


    static CepEntity createAEntityCep() {
        return CepEntity.builder()
                .cep("94020070")
                .logradouro("Rua Joao Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build();
    }

    static PessoaRequest createARequest() {
        return PessoaRequest.builder()
                .id("1")
                .nome("Camila")
                .dataDeNascimento("02/07/1996")
                .endereco(createAEntityCep())
                .build();
    }

    static PessoaResponse createAResponse() {
        return PessoaResponse.builder()
                .id("1")
                .nome("Camila")
                .dataDeNascimento("02/07/1996")
                .endereco(createAEntityCep())
                .build();
    }
}