package com.example.cepapi.integration.resttemplate;

import com.example.cepapi.integration.resttemplate.walmart.model.DataIntegrationResponse;
import com.example.cepapi.model.pessoa.cep.CepEntity;
import com.example.cepapi.model.pessoa.cep.response.CepResponse;
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

    public CepEntity consultarCep(String cep){
        return restTemplate.getForObject("https://viacep.com.br/ws/".concat(cep) + "/json", CepEntity.class);
    }
}
//TODO: INTEGRAR OUTRA APLICAÇÃO -
//TODO: MONGOTEMPLATE/QUERY DATAS -
//TODO: ESTUDAR PROPERTIES DO SPRING -