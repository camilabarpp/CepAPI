package com.example.cepapi.model.mapper;

import com.example.cepapi.model.Pessoa;
import com.example.cepapi.model.request.PessoaRequest;
import com.example.cepapi.model.response.PessoaResponse;

public class PessoaMapper {

    public static Pessoa requestPessoa(PessoaRequest pessoaRequest) {
        return Pessoa.builder()
                .id(pessoaRequest.getId())
                .nome(pessoaRequest.getNome())
                .dataDeNascimento(pessoaRequest.getDataDeNascimento())
                //.endereco(pessoaRequest.getEndereco())
                .build();
    }

    public static PessoaResponse pessoaResponse(Pessoa pessoa) {
        return PessoaResponse.builder()
                .nome(pessoa.getNome())
                .dataDeNascimento(pessoa.getDataDeNascimento())
                //.endereco(pessoa.getEndereco())
                .build();
    }
}
