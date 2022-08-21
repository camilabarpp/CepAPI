package com.example.cepapi.integration.resttemplate;

import com.example.cepapi.model.pessoa.response.PessoaResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class CepClient {
    RestTemplate restTemplate;
    public PessoaResponse consultaCep(@PathVariable String cep) {
        return this.restTemplate.getForObject("https://viacep.com.br/ws/" + cep + "/json/", PessoaResponse.class);
    }
}
