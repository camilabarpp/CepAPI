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
    @Autowired
    private CadastroRepository cadastroRepository;
    @Autowired
    private CepRepository cepRepository;

    @Autowired
    private CepService cepService;
    @Autowired
    private IntegrationCep cepIntegration;



    public PessoaResponse create(PessoaRequest pessoaRequest) {
        pesquisarCepESalvarNoBanco(pessoaRequest);
        return pessoaResponse(cadastroRepository.save(requestPessoa(pessoaRequest)));
    }

    public PessoaResponse save(PessoaRequest pessoaRequest) {
        //pesquisarCepESalvarNoBanco(pessoaRequest);
        return pessoaResponse(cadastroRepository.save(requestPessoa(pessoaRequest)));
    }

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
    public PessoaResponse update(PessoaRequest pessoaRequest,String id) {
        pesquisarCepESalvarNoBanco(pessoaRequest);

        Pessoa found = cadastroRepository.findById(id).orElseThrow(
                () -> new ApiNotFoundException("ID Not Found: " + id));
        found.setNome(pessoaRequest.getNome());
        found.setDataDeNascimento(pessoaRequest.getDataDeNascimento());
        found.setEndereco(pessoaRequest.getEndereco());
        Pessoa saved = cadastroRepository.save(found);
        return pessoaResponse(saved);
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

    private void pesquisarCepESalvarNoBanco(PessoaRequest pessoaRequest) {
        String cep = pessoaRequest.getEndereco().getCep();
        var endereco = cepRepository.findById((cep)).orElseGet(() -> {
            var novoEndereco = entityToResponse(this.cepIntegration.consultarCep(cep));
            cepRepository.save(novoEndereco);
            return novoEndereco;
        });
        pessoaRequest.setEndereco(endereco);
        cadastroRepository.save(requestPessoa(pessoaRequest));
    }
}
