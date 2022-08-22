package com.example.cepapi.service;

import com.example.cepapi.configuration.ApiNotFoundException;
import com.example.cepapi.integration.resttemplate.CepIntegration;
import com.example.cepapi.model.pessoa.cep.CepMapper;
import com.example.cepapi.model.pessoa.cep.response.CepResponse;
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
        return Optional.ofNullable(integration.consultaCep(cep))
                .map(CepMapper::toIntResponse)
                .map(repository::save)
                .map(CepMapper::toProductEntity)
                .orElseThrow(() -> new ApiNotFoundException("Please,insert a CEP"));
    }

}
