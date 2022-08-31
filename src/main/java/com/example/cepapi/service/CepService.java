package com.example.cepapi.service;

import com.example.cepapi.integration.resttemplate.cep.IntegrationCep;
import com.example.cepapi.model.cep.CepMapper;
import com.example.cepapi.model.cep.response.CepResponse;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.repository.CepRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.cepapi.model.cep.CepMapper.entityToResponse;

@Service
@AllArgsConstructor
public class CepService {

    private CepRepository cepRepository;
    private IntegrationCep integration;
    private CepRepository repository;

    public void pesquisarCepESalvarNoBanco(Pessoa pessoa) {
        String cep = pessoa.getEndereco().getCep();
        var endereco = cepRepository.findById((cep)).orElseGet(() -> {
            var novoEndereco = entityToResponse(this.integration.consultarCep(cep));
            cepRepository.save(novoEndereco);
            return novoEndereco;
        });
        pessoa.setEndereco(endereco);
    }

}
