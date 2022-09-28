package com.example.cepapi.cep.service;

import com.example.cepapi.configuration.resttemplate.cep.IntegrationCep;
import com.example.cepapi.cep.model.response.CepResponse;
import com.example.cepapi.registrationPeople.model.pessoa.Pessoa;
import com.example.cepapi.cep.repository.CepRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.cepapi.cep.model.CepMapper.entityToResponse;

@Service
@AllArgsConstructor
public class CepService {

    private CepRepository cepRepository;
    private IntegrationCep integration;

    public void pesquisarCepESalvarNoBanco(Pessoa pessoa) {
        String cep = pessoa.getEndereco().getCep();
        var endereco = cepRepository.findById((cep)).orElseGet(() -> {
            var novoEndereco = entityToResponse(this.integration.consultarCep(cep));
            cepRepository.save(novoEndereco);
            return novoEndereco;
        });
        pessoa.setEndereco(endereco);
    }

    public CepResponse consultarCep(String cep) {
       return integration.consultarCep(cep);
    }

}
