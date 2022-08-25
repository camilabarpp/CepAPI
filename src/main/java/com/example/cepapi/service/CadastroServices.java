package com.example.cepapi.service;

import com.example.cepapi.configuration.ApiNotFoundException;
import com.example.cepapi.integration.resttemplate.cep.IntegrationCep;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.mapper.PessoaMapper;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.repository.CadastroRepository;
import com.example.cepapi.repository.CepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.cepapi.model.cep.CepMapper.entityToResponse;
import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.pessoaResponse;
import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.requestPessoa;

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
        return cadastroRepository.save(pessoa);
    }

/*    public Pessoa save(Pessoa pessoaRequest) {
        //pesquisarCepESalvarNoBanco(pessoaRequest);
        return cadastroRepository.save(pessoaRequest);
    }*/

    //Método GET todos
    public List<PessoaResponse> findAll() {
        return cadastroRepository.findAll().stream()
                .map(PessoaMapper::pessoaResponse)
                .toList();
    }

    //Método GEt por ID
    public PessoaResponse findById(String id) {
        Pessoa pessoa = cadastroRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("ID Not Found: " + id));
        return pessoaResponse(pessoa);
    }

    //Method PUT
    public Pessoa update(String id, Pessoa pessoaAtt){
        pesquisarCepESalvarNoBanco(pessoaAtt);
        Pessoa pessoaSalva = cadastroRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Id não encontrado " + id));
        pessoaAtt.setId(pessoaSalva.getId());
        return cadastroRepository.save(pessoaAtt);
    }


    //Método DELETE
    public void delete(String id) {
        cadastroRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("ID Not Found: " + id));
        cadastroRepository.deleteById(id);
    }

    public void deletePeolpleByIDs(List<String> ids) {
        cadastroRepository.deleteAllById(ids);
    }

    public void deleteAll() {
        cadastroRepository.deleteAll();
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
