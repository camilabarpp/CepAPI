package com.example.cepapi.repository;

import com.example.cepapi.model.pessoa.cep.CepEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CepRepository extends MongoRepository<CepEntity, String> {
}
