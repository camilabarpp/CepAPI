package com.example.cepapi.repository;

import com.example.cepapi.model.pessoa.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroRepository extends MongoRepository<Pessoa, String>{

}
