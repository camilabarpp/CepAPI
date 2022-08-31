package com.example.cepapi.service;

import com.example.cepapi.configuration.exception.ApiNotFoundException;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.mapper.PessoaMapper;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.repository.CadastroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.pessoaResponse;

@Service
@RequiredArgsConstructor
public class CadastroServices {
    private CadastroRepository cadastroRepository;
    private CepService cepService;

    @Autowired
    public CadastroServices(CadastroRepository cadastroRepository, CepService cepService) {
        this.cadastroRepository = cadastroRepository;
        this.cepService = cepService;
    }

    public Pessoa create(Pessoa pessoa) {
        cepService.pesquisarCepESalvarNoBanco(pessoa);
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
/*    public PessoaResponse update(String id, Pessoa pessoa){
        cepService.pesquisarCepESalvarNoBanco(pessoa);
        Pessoa found = cadastroRepository.findById(id)
                .orElseThrow(
                () -> new ApiNotFoundException("ID Not Found: " + id));
        found.setNome(pessoa.getNome());
        found.setDataDeNascimento(pessoa.getDataDeNascimento());
        found.setEndereco(pessoa.getEndereco());
        Pessoa saved = cadastroRepository.save(found);
        return pessoaResponse(saved);
    }*/

    public Pessoa update(String id, Pessoa pessoa){
        cepService.pesquisarCepESalvarNoBanco(pessoa);
        Pessoa found = cadastroRepository.findById(id)
                .orElseThrow(
                        () -> new ApiNotFoundException("ID Not Found: " + id));
        found.setNome(pessoa.getNome());
        found.setDataDeNascimento(pessoa.getDataDeNascimento());
        found.setEndereco(pessoa.getEndereco());
        return cadastroRepository.save(found);
    }

    //Método DELETE
    public void deletePeolpleByIDs(List<String> id) {
        if (id == null) {
            cadastroRepository.deleteAll();
        } else {
            cadastroRepository.deleteAllById(id);
        }

    }
}
