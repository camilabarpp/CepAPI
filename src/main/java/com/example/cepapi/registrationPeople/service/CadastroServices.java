package com.example.cepapi.registrationPeople.service;

import com.example.cepapi.cafe.model.payment.PayPal;
import com.example.cepapi.cep.service.CepService;
import com.example.cepapi.configuration.exception.ApiNotFoundException;
import com.example.cepapi.registrationPeople.model.pessoa.Pessoa;
import com.example.cepapi.registrationPeople.model.pessoa.mapper.PessoaMapper;
import com.example.cepapi.registrationPeople.model.pessoa.response.PessoaResponse;
import com.example.cepapi.registrationPeople.repository.CadastroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CadastroServices {
    private CadastroRepository cadastroRepository;
    private CepService cepService;
    private WeatherService weatherService;

    public String teste(String id, PayPal.builder paypal) {
        var found = cadastroRepository.findById(id);
        if (Objects.equals(paypal.build().getEmail(), found.get().getPaypal().build().getEmail())) { //Está comparando os emails corretamente
            return "Sim";
        } else {
            return "Não";
        }
    }

    public Pessoa create(Pessoa pessoa) {
        cepService.pesquisarCepESalvarNoBanco(pessoa);
        weatherService.pesquisarTemperaturaESalvarNoBanco(pessoa);
        return cadastroRepository.save(pessoa);
    }
    public List<PessoaResponse> findAll() {
        return cadastroRepository.findAll().stream()
                .map(PessoaMapper::pessoaResponse)
                .toList();}
    public Pessoa findById(String id) {
        return cadastroRepository.findById(id)
                .orElseThrow(() ->
                        new ApiNotFoundException("ID '" + id + "' não encontrado!"));}
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
    public void deletePeolpleByIDs(List<String> id) {
        if (id == null) {
            cadastroRepository.deleteAll();
        } else {
            cadastroRepository.deleteAllById(id);
        }
    }
    public List<PessoaResponse> findByNome(String nome) {
        if (cadastroRepository.findByNome(nome).isEmpty()) {
            throw new ApiNotFoundException("Nome '" + nome + "' não encontrado!");
        } else {
            return cadastroRepository.findByNome(nome);
        }
    }
}
