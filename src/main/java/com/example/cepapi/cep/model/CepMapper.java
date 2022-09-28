package com.example.cepapi.cep.model;

import com.example.cepapi.cep.model.response.CepResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CepMapper {

    public static CepEntity entityToResponse(CepResponse response) {
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
