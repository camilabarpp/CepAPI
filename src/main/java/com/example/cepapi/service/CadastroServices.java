package com.example.cepapi.service;

import com.example.cepapi.model.request.PessoaRequest;
import com.example.cepapi.model.response.PessoaResponse;
import com.example.cepapi.repository.CadastroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@AllArgsConstructor
public class CadastroServices {
    private CadastroRepository repository;

    //Método GET todos
    public List<PessoaResponse> findAll(){
        return repository.findAll();
    }

    //Método GEt por ID
    public PessoaResponse findById(String id) {
        return repository.findById(id);
    }

    //Method PUT
    public PessoaResponse update(@RequestBody PessoaRequest pessoaRequest, @PathVariable String id) {
        return repository.update(pessoaRequest, id);
    }

/*    //Método POST
    public PessoaResponse create(PessoaRequest pessoaRequest) {
        return facade.create(pessoaRequest);
    }*/

    //Método DELETE
    public void delete(String id) {
        repository.delete(id);
    }

    //Método DELETE ids
    public void deleteByIDs(List<String> ids) {
        repository.deleteByIDs(ids);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}
