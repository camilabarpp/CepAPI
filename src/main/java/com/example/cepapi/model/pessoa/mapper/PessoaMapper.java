package com.example.cepapi.model.pessoa.mapper;

import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Optional;

@UtilityClass
public class PessoaMapper {

    public static Pessoa requestPessoa(PessoaRequest pessoaRequest) {
        return Pessoa.builder()
                //.id(pessoaRequest.getId())
                .nome(pessoaRequest.getNome())
                .dataDeNascimento(pessoaRequest.getDataDeNascimento())
                .endereco(pessoaRequest.getEndereco())
                .build();
    }


    public static PessoaResponse pessoaResponse(Pessoa pessoa) {
        return PessoaResponse.builder()
                .id(pessoa.getId()) //Não tirar, senão não aparece o id no FindAll
                .nome(pessoa.getNome())
                .dataDeNascimento(pessoa.getDataDeNascimento())
                .endereco(pessoa.getEndereco())
                .build();
    }

//    public static Pessoa toEntity(PessoaResponse pessoaResponse){
//        return Pessoa.builder()
//                //.id(pessoaResponse.getId())
//                .nome(pessoaResponse.getNome())
//                .dataDeNascimento(pessoaResponse.getDataDeNascimento())
//                .endereco(pessoaResponse.getEndereco())
//                .build();
//    }

    public static Optional<Pessoa> toEntityOptional(PessoaResponse pessoaResponse){
        return Optional.ofNullable(Pessoa.builder()
               // .id(pessoaResponse.getId())
                .nome(pessoaResponse.getNome())
                .dataDeNascimento(pessoaResponse.getDataDeNascimento())
                .endereco(pessoaResponse.getEndereco())
                .build());
    }

    public static PessoaResponse toRequest(PessoaRequest pessoaRequest){
        return PessoaResponse.builder()
                //.id(pessoaRequest.getId())
                .nome(pessoaRequest.getNome())
                .dataDeNascimento(pessoaRequest.getDataDeNascimento())
                .endereco(pessoaRequest.getEndereco())
                .build();
    }

/*    public static CepResponse cepToPessoaResponse(PessoaResponse pessoaRequest){
        return CepResponse.builder()
                .cep(pessoaRequest.getEndereco().getCep())
                .logradouro(pessoaRequest.getEndereco().getLogradouro())
                .bairro(pessoaRequest.getEndereco().getBairro())
                .localidade(pessoaRequest.getEndereco().getLocalidade())
                .uf(pessoaRequest.getEndereco().getUf())
                .build();
    }*/

}
