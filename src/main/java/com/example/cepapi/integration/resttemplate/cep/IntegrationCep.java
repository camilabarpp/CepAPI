package com.example.cepapi.integration.resttemplate.cep;

import com.example.cepapi.model.cep.response.CepResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@AllArgsConstructor
@Component
public class IntegrationCep {
    @Qualifier("restTemplateCep")
    private RestTemplate restTemplate;

    public CepResponse consultarCep(String cep){
        return restTemplate.getForObject("/ws/".concat(cep) + "/json", CepResponse.class);
    }
}
