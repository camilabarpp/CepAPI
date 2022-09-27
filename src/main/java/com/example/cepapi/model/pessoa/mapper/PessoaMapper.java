package com.example.cepapi.model.pessoa.mapper;

import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class PessoaMapper{

    public static Pessoa requestPessoa(PessoaRequest pessoaRequest) {
        return Pessoa.builder()
                //.id(pessoaRequest.getId())
                .nome(pessoaRequest.getNome())
                .dataDeNascimento(pessoaRequest.getDataDeNascimento())
                .endereco(pessoaRequest.getEndereco())
                .temperatura(pessoaRequest.getTemperatura())
                .debitCard(pessoaRequest.getDebitCard())
                .creditCard(pessoaRequest.getCreditCard())
                .paypal(pessoaRequest.getPaypal())
                .build();
    }


    public static PessoaResponse pessoaResponse(Pessoa pessoa) {
        return PessoaResponse.builder()
                .id(pessoa.getId()) //Não tirar, senão não aparece o "id" no FindAll
                .nome(pessoa.getNome())
                .dataDeNascimento(pessoa.getDataDeNascimento())
                .endereco(pessoa.getEndereco())
                .temperatura(pessoa.getTemperatura())
                .build();
    }
    public static PessoaResponse toRequest(PessoaRequest pessoaRequest){
        return PessoaResponse.builder()
                //.id(pessoaRequest.getId())
                .nome(pessoaRequest.getNome())
                .dataDeNascimento(pessoaRequest.getDataDeNascimento())
                .endereco(pessoaRequest.getEndereco())
                .temperatura(pessoaRequest.getTemperatura())
                .build();
    }

    public static Optional<Pessoa> optionalToEntity(Pessoa pessoa){
        return Optional.ofNullable(Pessoa.builder()
                .nome(pessoa.getNome())
                .dataDeNascimento(pessoa.getDataDeNascimento())
                .endereco(pessoa.getEndereco())
                .temperatura(pessoa.getTemperatura())
                .debitCard(pessoa.getDebitCard())
                .creditCard(pessoa.getCreditCard())
                .paypal(pessoa.getPaypal())
                .build());
    }
}
