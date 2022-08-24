package com.example.cepapi.service;

import com.example.cepapi.integration.resttemplate.cep.IntegrationCep;
import com.example.cepapi.model.cep.CepMapper;
import com.example.cepapi.model.cep.response.CepResponse;
import com.example.cepapi.repository.CepRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CepService {

    private IntegrationCep integration;

    private CepRepository repository;
    public CepResponse consultaCep(String cep) {
        var cepResponseIntegration = integration.consultarCep(cep);
        var entity = CepMapper.entityToResponse(cepResponseIntegration);
        var entitySaved = repository.save(entity);
        return CepMapper.toProductEntity(entitySaved);
    }

}
