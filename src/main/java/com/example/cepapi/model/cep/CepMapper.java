package com.example.cepapi.model.cep;

import com.example.cepapi.model.cep.response.CepResponse;

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

    public static CepResponse toProductEntity(CepEntity entity){
//        var product = entity.getData().getProduct();
        return CepResponse.builder()
                .cep(entity.getCep())
                .logradouro(entity.getLogradouro())
                .bairro(entity.getBairro())
                .localidade(entity.getLocalidade())
                .uf(entity.getUf())
                .build();
    }
}
