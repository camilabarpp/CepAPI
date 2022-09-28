package com.example.cepapi.cep.repository;

import com.example.cepapi.cep.model.CepEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CepRepository extends MongoRepository<CepEntity, String> {
}
