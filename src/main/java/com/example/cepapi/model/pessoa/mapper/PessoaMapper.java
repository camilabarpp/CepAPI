package com.example.cepapi.model.pessoa.mapper;

import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PessoaMapper {

    public static Pessoa requestPessoa(PessoaRequest pessoaRequest) {
        return Pessoa.builder()
                .nome(pessoaRequest.getNome())
                .dataDeNascimento(pessoaRequest.getDataDeNascimento())
                .cep(pessoaRequest.getCep())
                .localidade(pessoaRequest.getLocalidade())
                .numero(pessoaRequest.getNumero())
                .bairro(pessoaRequest.getBairro())
                .logradouro(pessoaRequest.getLogradouro())
                .uf(pessoaRequest.getUf())
                .build();
    }

    public static PessoaResponse pessoaResponse(Pessoa pessoa) {
        return PessoaResponse.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .dataDeNascimento(pessoa.getDataDeNascimento())
                .cep(pessoa.getCep())
                .logradouro(pessoa.getLogradouro())
                .numero(pessoa.getNumero())
                .bairro(pessoa.getBairro())
                .localidade(pessoa.getLocalidade())
                .uf(pessoa.getUf())
                .build();
    }

    public static Pessoa toEntity(PessoaResponse pessoaResponse){
        return Pessoa.builder()
                .nome(pessoaResponse.getNome())
                .dataDeNascimento(pessoaResponse.getDataDeNascimento())
                .logradouro(pessoaResponse.getLogradouro())
                .numero(pessoaResponse.getNumero())
                .bairro(pessoaResponse.getBairro())
                .localidade(pessoaResponse.getLocalidade())
                .uf(pessoaResponse.getUf())
                .build();
    }
}
