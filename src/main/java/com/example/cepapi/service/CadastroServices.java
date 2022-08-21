package com.example.cepapi.service;

import com.example.cepapi.configuration.ApiNotFoundException;
import com.example.cepapi.integration.resttemplate.CepClient;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.mapper.PessoaMapper;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.repository.CadastroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public PessoaResponse update(PessoaRequest pessoaRequest,String id) {
        var consultaCep = client.consultaCep(pessoaRequest.getCep());

        Pessoa found = cadastroRepository.findById(id).orElseThrow(
                () -> new ApiNotFoundException("ID Not Found: " + id));
        found.setNome(pessoaRequest.getNome());
        found.setDataDeNascimento(pessoaRequest.getDataDeNascimento());
        found.setCep(consultaCep.getCep());
        found.setLogradouro(consultaCep.getLogradouro());
        found.setNumero(pessoaRequest.getNumero());
        found.setBairro(consultaCep.getBairro());
        found.setLocalidade(consultaCep.getLocalidade());
        found.setUf(consultaCep.getUf());
        Pessoa saved = cadastroRepository.save(found);
        return pessoaResponse(saved);
    }

        //Método POST
    public PessoaResponse create(PessoaRequest pessoaRequest) { //Está funcionando corretamente
        var cepPesquisado = client.consultaCep(pessoaRequest.getCep());
        integration(pessoaRequest, cepPesquisado);
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

    private static void integration(PessoaRequest pessoaRequest, PessoaResponse cepPesquisado) {
        pessoaRequest.setCep(cepPesquisado.getCep());
        pessoaRequest.setLogradouro(cepPesquisado.getLogradouro());
        pessoaRequest.setBairro(cepPesquisado.getBairro());
        pessoaRequest.setLocalidade(cepPesquisado.getLocalidade());
        pessoaRequest.setUf(cepPesquisado.getUf());
    }
}
