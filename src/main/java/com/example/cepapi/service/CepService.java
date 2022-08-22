package com.example.cepapi.service;

import com.example.cepapi.configuration.ApiNotFoundException;
import com.example.cepapi.integration.resttemplate.cep.CepIntegration;
import com.example.cepapi.model.cep.CepMapper;
import com.example.cepapi.model.cep.request.CepRequest;
import com.example.cepapi.model.cep.response.CepResponse;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.repository.CepRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CepService {

    private CepIntegration integration;

    private CepRepository repository;
    public CepResponse consultaCep(String cep) {
        var cepResponseIntegration = integration.consultarCep(cep);
        var entity = CepMapper.entityToResponse(cepResponseIntegration);
        var entitySaved = repository.save(entity);
        return CepMapper.toProductEntity(entitySaved);
    }

}
