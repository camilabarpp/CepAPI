package com.example.cepapi.service;

import com.example.cepapi.configuration.exception.ApiNotFoundException;
import com.example.cepapi.integration.resttemplate.cep.IntegrationCep;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.mapper.PessoaMapper;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.repository.CadastroRepository;
import com.example.cepapi.repository.CepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.cepapi.model.cep.CepMapper.entityToResponse;
import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.pessoaResponse;

@Service
@RequiredArgsConstructor
public class CadastroServices {
    private CadastroRepository cadastroRepository;
    private CepRepository cepRepository;

    private CepService cepService;
    private IntegrationCep cepIntegration;

    @Autowired
    public CadastroServices(CadastroRepository cadastroRepository, CepRepository cepRepository, CepService cepService, IntegrationCep cepIntegration) {
        this.cadastroRepository = cadastroRepository;
        this.cepRepository = cepRepository;
        this.cepService = cepService;
        this.cepIntegration = cepIntegration;
    }

    public Pessoa create(Pessoa pessoa) {
        pesquisarCepESalvarNoBanco(pessoa);
        return cadastroRepository.insert(pessoa);
    }

    //Método GET todos
    public List<PessoaResponse> findAll() {
        return cadastroRepository.findAll().stream()
                .map(PessoaMapper::pessoaResponse)
                .toList();
    }

    //Método GEt por ID
    public PessoaResponse findById(String id) {
        return cadastroRepository.findById(id)
                .map(PessoaMapper::pessoaResponse)
                .orElseThrow(() -> new ApiNotFoundException("ID Not Found: " + id));
    }

    //Method PUT
    public PessoaResponse update(String id, Pessoa pessoa){
        pesquisarCepESalvarNoBanco(pessoa);
        Pessoa found = cadastroRepository.findById(id)
                .orElseThrow(
                () -> new ApiNotFoundException("ID Not Found: " + id));
        found.setNome(pessoa.getNome());
        found.setDataDeNascimento(pessoa.getDataDeNascimento());
        found.setEndereco(pessoa.getEndereco());
        Pessoa saved = cadastroRepository.save(found);
        return pessoaResponse(saved);
    }

    //Método DELETE
    public void deletePeolpleByIDs(List<String> id) {
        if (id == null) {
            cadastroRepository.deleteAll();
        } else {
            cadastroRepository.deleteAllById(id);
        }

    }

    private void pesquisarCepESalvarNoBanco(Pessoa pessoa) {
        String cep = pessoa.getEndereco().getCep();
        var endereco = cepRepository.findById((cep)).orElseGet(() -> {
            var novoEndereco = entityToResponse(this.cepIntegration.consultarCep(cep));
            cepRepository.save(novoEndereco);
            return novoEndereco;
        });
        pessoa.setEndereco(endereco);
    }
}
