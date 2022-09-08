package com.example.cepapi.model.cep;

import com.example.cepapi.model.cep.response.CepResponse;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.response.PessoaResponse;

import java.util.Optional;

public class CepMapper {

   public static CepEntity entityToResponse(CepResponse response) {
        //var product = response.getData().getProduct();
        return CepEntity.builder()
                .cep(response.getCep())
                .logradouro(response.getLogradouro())
                .bairro(response.getBairro())
                .localidade(response.getLocalidade())
                .uf(response.getUf())
                .build();
    }

    public static CepResponse responseToEntity(CepEntity cepEntity) {
        return CepResponse.builder()
                .cep(cepEntity.getCep())
                .logradouro(cepEntity.getLogradouro())
                .bairro(cepEntity.getBairro())
                .localidade(cepEntity.getLocalidade())
                .uf(cepEntity.getUf())
                .build();
    }


}
