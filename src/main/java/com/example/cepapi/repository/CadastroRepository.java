package com.example.cepapi.repository;

import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CadastroRepository extends MongoRepository<Pessoa, String> {

    //Query Param com 1 parâmeto
    @Query("{ 'nome': { $regex: ?0, $options:  'i' } }")
    List<PessoaResponse> findByNome(String nome);
}

