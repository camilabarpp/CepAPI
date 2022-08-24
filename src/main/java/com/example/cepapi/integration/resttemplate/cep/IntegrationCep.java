package com.example.cepapi.integration.resttemplate.cep;

import com.example.cepapi.model.cep.response.CepResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@NoArgsConstructor
@Component
public class IntegrationCep {
    @Autowired
    private RestTemplate restTemplate;

    public CepResponse consultarCep(String cep){
        return restTemplate.getForObject("https://viacep.com.br/ws/".concat(cep) + "/json", CepResponse.class);
    }
}
//TODO: INTEGRAR OUTRA APLICAÇÃO -
//TODO: MONGOTEMPLATE/QUERY DATAS -
//TODO: ESTUDAR PROPERTIES DO SPRING -