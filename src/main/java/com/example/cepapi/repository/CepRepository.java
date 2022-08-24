package com.example.cepapi.repository;

import com.example.cepapi.model.cep.CepEntity;
import com.example.cepapi.model.cep.response.CepResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CepRepository extends MongoRepository<CepEntity, String> {
    CepEntity findByCep(String cep);
}
