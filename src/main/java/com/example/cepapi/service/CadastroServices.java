package com.example.cepapi.service;

import com.example.cepapi.model.Pessoa;
import com.example.cepapi.repository.CadastroRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class CadastroServices {

    private final CadastroRepository cadastroRepository;
    public CadastroServices(CadastroRepository cadastroRepository) {
        this.cadastroRepository = cadastroRepository;
    }

    //Método GET todos
    public List<Pessoa> findAll() {
        return this.cadastroRepository.findAll();
    }

    //Método GEt por ID
    public Pessoa findById(String id) {
         return this.cadastroRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Material inexistente"));
        //adicionar a excessao
    }

    //Method PUT
    public Pessoa update(@RequestBody Pessoa newPessoa, @PathVariable String id) {
        return cadastroRepository.findById(id)
                .map(pessoa -> {
                    pessoa.setNome(newPessoa.getNome());
                    pessoa.setDataDeNascimento(newPessoa.getDataDeNascimento());
                    pessoa.setCep(newPessoa.getCep());
                    pessoa.setLogradouro(newPessoa.getLogradouro());
                    pessoa.setNumero(newPessoa.getNumero());
                    pessoa.setBairro(newPessoa.getBairro());
                    pessoa.setLocalidade(newPessoa.getLocalidade());
                    pessoa.setUf(newPessoa.getUf());
                    return cadastroRepository.save(pessoa);
                })
                .orElseGet(() -> {
                    newPessoa.setId(id);
                    return cadastroRepository.save(newPessoa);
                });
    }

    //Método POST
    public Pessoa create(Pessoa pessoa) {
        return this.cadastroRepository.insert(pessoa);
    }

    //Método DELETE
    public void delete(String id) {
        findById(id);
        this.cadastroRepository.deleteById(id);
    }

    public Pessoa consultaCep(@PathVariable String cep) {
        return new RestTemplate().getForEntity("https://viacep.com.br/ws/" + cep + "/json/", Pessoa.class).getBody();
    }
}
