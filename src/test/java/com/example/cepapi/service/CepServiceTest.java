package com.example.cepapi.service;

import com.example.cepapi.configuration.ApiNotFoundException;
import com.example.cepapi.integration.resttemplate.CepIntegration;
import com.example.cepapi.repository.CadastroRepository;
import com.example.cepapi.repository.CepRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static com.example.cepapi.service.CepServiceStub.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CadastroServices.class, CadastroRepository.class, CepIntegration.class})
@ExtendWith(MockitoExtension.class)
public class CepServiceTest {

    @Mock
    private CepRepository repository;

    @InjectMocks
    private CepService service;

    @Mock
    private CepIntegration integration;

    @Test
    @DisplayName("Pesquisar por cep e salvar no banco de dados")
    void retornarProcuraNaIntegração() {
        var expectedServiceResponse = CepServiceResponseExpectedStub();
        var ProductEntity = cepEntityStub();
        var expectedIntegrationResponse = CepIntegrationStubResponse();

        when(integration.consultaCep("94020070")).thenReturn(expectedIntegrationResponse);

        when(repository.save(ProductEntity)).thenReturn(ProductEntity);

        var actual = service.consultaCep("94020070");

        assertEquals(expectedServiceResponse, actual);
    }

    @Test
    @DisplayName("Retornando um ApiNotFound se nulo")
    void whenNotFindProductIntegrationBadRequest() {

        var expectedIntegrationResponse = CepServiceStubBadRequest();

        when(integration.consultaCep("94020070")).thenReturn(expectedIntegrationResponse);

        Exception exception = assertThrows(ApiNotFoundException.class,
                () -> service.consultaCep("94020070"));

        var expectativa = "Please,insert a CEP";
        var actual = exception.getMessage();

        assertEquals(actual, expectativa);
    }
}
