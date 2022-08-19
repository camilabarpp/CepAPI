package com.example.cepapi.repository;

import com.example.cepapi.model.Pessoa;
import com.example.cepapi.model.request.PessoaRequest;
import com.example.cepapi.model.response.PessoaResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CadastroRepository extends MongoRepository<Pessoa, String>{

}
