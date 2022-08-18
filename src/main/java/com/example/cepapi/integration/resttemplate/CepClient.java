package com.example.cepapi.integration.resttemplate;

import com.example.cepapi.model.request.PessoaRequest;
import com.example.cepapi.model.response.PessoaResponse;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@AllArgsConstructor
public class CepClient {
    RestTemplate restTemplate;
    public PessoaResponse consultaCep(@PathVariable String cep) {
        return this.restTemplate.getForObject("https://viacep.com.br/ws/" + cep + "/json/", PessoaResponse.class);
    }
}
