package com.example.cepapi.configuration.resttemplate.cep;

import com.example.cepapi.cep.model.response.CepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class IntegrationCep {
    private final RestTemplate restTemplate;
    @Autowired
    public IntegrationCep(@Qualifier("restTemplateCep") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CepResponse consultarCep(String cep){
        return restTemplate.getForObject("/ws/".concat(cep) + "/json", CepResponse.class);
    }
}
