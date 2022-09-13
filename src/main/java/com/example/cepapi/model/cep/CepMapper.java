package com.example.cepapi.model.cep;

import com.example.cepapi.model.cep.response.CepResponse;

public class CepMapper {
    private CepMapper() {
    }

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
