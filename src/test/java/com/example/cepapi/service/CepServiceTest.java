package com.example.cepapi.service;

import com.example.cepapi.integration.resttemplate.WalmartIntegration;
import com.example.cepapi.repository.CadastroRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static com.example.cepapi.service.CepServiceStub.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CadastroServices.class, CadastroRepository.class, WalmartIntegration.class})
@ExtendWith(MockitoExtension.class)
public class CepServiceTest {

    @Mock
    private CadastroRepository repository;

    @InjectMocks
    private CadastroServices service;

    @Mock
    private WalmartIntegration integration;

    @Test
    @DisplayName("Procurando na integração e salvando no banco de dados")
    void retornarProcuraNaIntegração() {
        var expectedServiceResponse = CepServiceResponseExpectedStub();
        var ProductEntity = cepEntityStub();
        var expectedIntegrationResponse = CepIntegrationStubResponse();

        when(integration.findProductDetails("94020070")).thenReturn(expectedIntegrationResponse);

        when(repository.save(ProductEntity)).thenReturn(ProductEntity);

        var actual = service.create("94020070");

        assertEquals(expectedServiceResponse, actual);
    }
}
