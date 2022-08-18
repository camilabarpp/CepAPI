package com.example.cepapi.service;

import com.example.cepapi.configuration.ApiNotFoundException;
import com.example.cepapi.integration.resttemplate.CepClient;
import com.example.cepapi.model.Pessoa;
import com.example.cepapi.model.mapper.PessoaMapper;
import com.example.cepapi.model.request.PessoaRequest;
import com.example.cepapi.model.response.PessoaResponse;
import com.example.cepapi.repository.CadastroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.example.cepapi.model.mapper.PessoaMapper.pessoaResponse;
import static com.example.cepapi.model.mapper.PessoaMapper.requestPessoa;

@Service
@AllArgsConstructor
public class CadastroServices {

    private final CadastroRepository cadastroRepository;

    private CepClient client;

    //Método GET todos
    public List<PessoaResponse> findAll(){
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
/*    public PessoaResponse update(@RequestBody PessoaRequest newPessoa, @PathVariable String id) {
        Pessoa found = cadastroRepository.findById(id).orElseThrow(
                () -> new ApiNotFoundException("ID Not Found: " + id));
        found.setNome(newPessoa.getNome());
        found.setDataDeNascimento(newPessoa.getDataDeNascimento());
        found.setCep(newPessoa.getCep());
        found.setLogradouro(newPessoa.getLogradouro());
        found.setNumero(newPessoa.getNumero());
        found.setBairro(newPessoa.getBairro());
        found.setLocalidade(newPessoa.getLocalidade());
        found.setUf(newPessoa.getUf());
        Pessoa saved = cadastroRepository.save(found);
        return pessoaResponse(saved);
    }*/

    //Método POST
    public PessoaResponse create(PessoaRequest pessoaRequest) { //Está funcionando corretamente

        return pessoaResponse(cadastroRepository.insert((requestPessoa(pessoaRequest))));
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

}
