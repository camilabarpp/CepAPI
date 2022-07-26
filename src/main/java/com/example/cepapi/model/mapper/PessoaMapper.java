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
                //.cep(pessoaRequest.getCep())
                //.logradouro(pessoaRequest.getLogradouro())
                .numero(pessoaRequest.getNumero())
                //.bairro(pessoaRequest.getBairro())
                //.localidade(pessoaRequest.getLocalidade())
                //.uf(pessoaRequest.getUf())
                .build();
    }

    public static PessoaResponse pessoaResponse(Pessoa pessoa) {
        return PessoaResponse.builder()
                .nome(pessoa.getNome())
                .dataDeNascimento(pessoa.getDataDeNascimento())
                //.cep(pessoa.getCep())
                //.logradouro(pessoa.getLogradouro())
                .numero(pessoa.getNumero())
                //.bairro(pessoa.getBairro())
                //.localidade(pessoa.getLocalidade())
                //.uf(pessoa.getUf())
                .build();
    }
}
