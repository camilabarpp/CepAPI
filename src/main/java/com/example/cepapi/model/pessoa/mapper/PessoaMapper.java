package com.example.cepapi.model.pessoa.mapper;

import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PessoaMapper {

    public static Pessoa requestPessoa(PessoaRequest pessoaRequest) {
        return Pessoa.builder()
                .id(pessoaRequest.getId())
                .nome(pessoaRequest.getNome())
                .dataDeNascimento(pessoaRequest.getDataDeNascimento())
                .endereco(pessoaRequest.getEndereco())
                .build();
    }

    public static PessoaResponse pessoaResponse(Pessoa pessoa) {
        return PessoaResponse.builder()
                .nome(pessoa.getNome())
                .dataDeNascimento(pessoa.getDataDeNascimento())
                .endereco(pessoa.getEndereco())
                .build();
    }

    public static Pessoa toEntity(PessoaResponse pessoaResponse){
        return Pessoa.builder()
                .nome(pessoaResponse.getNome())
                .dataDeNascimento(pessoaResponse.getDataDeNascimento())
                .endereco(pessoaResponse.getEndereco())
                .build();
    }

    public static PessoaResponse toRequest(PessoaRequest pessoaRequest){
        return PessoaResponse.builder()
                .nome(pessoaRequest.getNome())
                .dataDeNascimento(pessoaRequest.getDataDeNascimento())
                .endereco(pessoaRequest.getEndereco())
                .build();
    }
}
