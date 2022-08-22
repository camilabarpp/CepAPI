package com.example.cepapi.integration;

import com.example.cepapi.model.DataIntegrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
//@NoArgsConstructor
public class CepIntegration {
    @Autowired
    private RestTemplate restTemplate;

    public DataIntegrationResponse consultaCep(String cep){
        return restTemplate.getForObject("https://viacep.com.br/ws/".concat(cep) + "/json", DataIntegrationResponse.class);
    }
}
//TODO: INTEGRAR OUTRA APLICAÇÃO -
//TODO: MONGOTEMPLATE/QUERY DATAS -
//TODO: ESTUDAR PROPERTIES DO SPRING -