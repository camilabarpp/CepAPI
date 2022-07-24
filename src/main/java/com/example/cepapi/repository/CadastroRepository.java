package com.example.cepapi.repository;

import com.example.cepapi.model.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroRepository extends MongoRepository<Pessoa, String>{

    /*
        //Query Param com 1 parâmeto
    @Query("{ 'nome': { $regex: ?0, $options:  'i' } }")
    List<Material> findByNome(String nome);

    //Query Param com mais parâmetos
    @Query("{ $and: [ " +
            "{ 'nome': { $regex: ?0, $options: 'i' } }," +
            "{ 'marca': { $regex: ?1, $options: 'i' } }]}")
    List<Material> fullSearch(String nome, String marca);
     */
}
