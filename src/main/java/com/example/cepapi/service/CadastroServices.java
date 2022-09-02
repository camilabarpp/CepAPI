package com.example.cepapi.service;

import com.example.cepapi.configuration.exception.ApiNotFoundException;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.mapper.PessoaMapper;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.repository.CadastroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CadastroServices {
    private CadastroRepository cadastroRepository;
    private CepService cepService;
    private WeatherService weatherService;

    public Pessoa create(Pessoa pessoa) {
        cepService.pesquisarCepESalvarNoBanco(pessoa);
        weatherService.pesquisarTemperaturaESalvarNoBanco(pessoa);
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
    public Pessoa update(String id, Pessoa pessoa){
        cepService.pesquisarCepESalvarNoBanco(pessoa);
        Pessoa found = cadastroRepository.findById(id)
                .orElseThrow(
                        () -> new ApiNotFoundException("ID Not Found: " + id));
        found.setNome(pessoa.getNome());
        found.setDataDeNascimento(pessoa.getDataDeNascimento());
        found.setEndereco(pessoa.getEndereco());
        weatherService.pesquisarTemperaturaESalvarNoBanco(pessoa);
        found.setTemperatura(pessoa.getTemperatura());
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

/*
    public List<Pessoa> findByNome(String nome) {
        return cadastroRepository.findByNomeContains(nome);
    }
*/

    public List<PessoaResponse> findByNome(String nome) {
        return cadastroRepository.findByNome(nome);
    }
}
