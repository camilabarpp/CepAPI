package com.example.cepapi.service;

import com.example.cepapi.configuration.ApiNotFoundException;
import com.example.cepapi.integration.resttemplate.CepIntegration;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.cep.CepEntity;
import com.example.cepapi.model.pessoa.cep.response.CepResponse;
import com.example.cepapi.model.pessoa.mapper.PessoaMapper;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.repository.CadastroRepository;
import com.example.cepapi.repository.CepRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.cepapi.model.pessoa.cep.CepMapper.entityToResponse;
import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.pessoaResponse;
import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.requestPessoa;

@Service
@AllArgsConstructor
public class CadastroServices {

    private CadastroRepository cadastroRepository;

    private CepRepository cepRepository;

    private CepService cepService;

    private CepIntegration integration;

    public PessoaResponse inserir(PessoaRequest request) {
       preencherCliente(request);
       return PessoaMapper.toRequest(request);
    }

    private PessoaResponse preencherCliente(PessoaRequest pessoaRequest) {
        String cep = pessoaRequest.getEndereco().getCep();
        CepEntity endereco = cepRepository.findById((cep)).orElseGet(() -> {
            CepResponse novoEndereco = integration.consultarCep(cep);
            cepRepository.save(entityToResponse(novoEndereco));
            pessoaRequest.getEndereco().setCep(novoEndereco.getCep());
            pessoaRequest.getEndereco().setLogradouro(novoEndereco.getLogradouro());
            pessoaRequest.getEndereco().setBairro(novoEndereco.getBairro());
            pessoaRequest.getEndereco().setLocalidade(novoEndereco.getLocalidade());
            pessoaRequest.getEndereco().setUf(novoEndereco.getUf());
            return entityToResponse(novoEndereco);
        });
        pessoaRequest.setEndereco(endereco);
        return pessoaResponse(cadastroRepository.insert((requestPessoa(pessoaRequest))));
    }

/*    private static void integration(PessoaRequest pessoaRequest, CepEntity cepPesquisado) {
        pessoaRequest.setEndereco(cepPesquisado.getCep());
        pessoaRequest.setCepEntity(cepPesquisado.getLogradouro());
        pessoaRequest.setBairro(cepPesquisado.getBairro());
        pessoaRequest.setLocalidade(cepPesquisado.getLocalidade());
        pessoaRequest.setUf(cepPesquisado.getUf());
    }*/

    //Método POST
/*    public PessoaResponse create(PessoaRequest pessoaRequest) { //Está funcionando corretamente
        var cepPesquisado = client.consultaCep(pessoaRequest.getCep());
        integration(pessoaRequest, cepPesquisado);
        return pessoaResponse(cadastroRepository.insert((requestPessoa(pessoaRequest))));
    }*/

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
/*    public PessoaResponse update(PessoaRequest pessoaRequest,String id) {
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
    }*/



    //Método DELETE
    public void delete(String id) {
        cadastroRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("ID Not Found: " + id));
        cadastroRepository.deleteById(id);
    }

    public void deletePeolpleByIDs(List<String> ids) {
        cadastroRepository.deleteAllById(ids);
    }

    public void deleteAll(){
        cadastroRepository.deleteAll();
    }

/*    private static void integration(PessoaRequest pessoaRequest, PessoaResponse cepPesquisado) {
        pessoaRequest.setCep(cepPesquisado.getCep());
        pessoaRequest.setLogradouro(cepPesquisado.getLogradouro());
        pessoaRequest.setBairro(cepPesquisado.getBairro());
        pessoaRequest.setLocalidade(cepPesquisado.getLocalidade());
        pessoaRequest.setUf(cepPesquisado.getUf());
    }*/
}
