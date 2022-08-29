package com.example.cepapi.service;

import com.example.cepapi.model.cep.CepEntity;
import com.example.cepapi.model.cep.response.CepResponse;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.response.PessoaResponse;

import java.util.Optional;

public class CadastroServiceStub {

/*    static DataIntegrationResponse CepIntegrationStubResponse(){
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
    }*/
/*    static DataIntegrationResponse CepServiceStubBadRequest(){
        return DataIntegrationResponse.builder()
                .data(ResponseData.builder()
                        .product(ResponseProduct.builder()
                                .cep(null)
                                .logradouro(null)
                                .bairro(null)
                                .localidade(null)
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

    static Optional<Pessoa> PessoaServiceStubExpected(){
        return Optional.ofNullable(Pessoa.builder()
                .id("1")
                .nome("94020070")
                .dataDeNascimento("Rua Joao Dutra")
                .build());
    }

    static Optional<Pessoa> PessoaServiceStubResponse(){
        return Optional.ofNullable(Pessoa.builder()
                .id("1")
                .nome("94020070")
                .dataDeNascimento("Rua Joao Dutra")
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

    static PessoaResponse PessoaServiceResponseExpectedStub(){
        return PessoaResponse.builder()
                .id("1")
                .nome("Camila")
                .dataDeNascimento("02/07/1996")
/*                .enderec
                .cep("94020070")
                .logradouro("Rua Joao Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")*/
                .build();
    }

    static Pessoa pessoaEntityStub(){
        return  Pessoa.builder()
                .id("1")
                .nome("Camila")
                .dataDeNascimento("02/07/1996")
                .build();
    }

}
