package com.example.cepapi.integration.resttemplate;

import com.example.cepapi.integration.resttemplate.walmart.model.DataIntegrationResponse;
import demo.sprint.integration.walmart.model.DataIntegrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
//@NoArgsConstructor
public class WalmartIntegration {
    @Autowired
    private RestTemplate restTemplate;

    public DataIntegrationResponse findProductDetails(String cep){
        return restTemplate.getForObject("https://viacep.com.br/ws/".concat(cep) + "/json", DataIntegrationResponse.class);
    }
}
//TODO: INTEGRAR OUTRA APLICAÇÃO -
//TODO: MONGOTEMPLATE/QUERY DATAS -
//TODO: ESTUDAR PROPERTIES DO SPRING -