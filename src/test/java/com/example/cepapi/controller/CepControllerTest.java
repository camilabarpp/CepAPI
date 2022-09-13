package com.example.cepapi.controller;

import com.example.cepapi.configuration.exception.ApiNotFoundException;
import com.example.cepapi.model.cep.CepEntity;
import com.example.cepapi.service.CepService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.cepapi.controller.stub.PessoaControllerStub.createAEntityCep;
import static com.example.cepapi.model.cep.CepMapper.responseToEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CepControllerTest {
    @InjectMocks
    private CepController controller;
    @Mock
    private CepService service;

    @Test
    @DisplayName("Deve procurar um cep com sucesso")
    void shouldFindCepWithSuccess() {
        String cep = "94020070";
        CepEntity entity = createAEntityCep();

        when(service.consultarCep(cep)).thenReturn(responseToEntity((entity)));

        var response = controller.getCep(cep);

        assertNotNull(response);
        assertEquals(entity.getCep(), response.getCep());
        assertEquals(entity.getLogradouro(), response.getLogradouro());
        assertEquals(entity.getBairro(), response.getBairro());
        assertEquals(entity.getLocalidade(), response.getLocalidade());
        assertEquals(entity.getUf(), response.getUf());
    }

    @Test
    @DisplayName("Deve lançar ApiNotFoundException quando não achar o cep")
    void shouldNotFindCepAndThrowsApiNotFoundException() {
        doThrow(ApiNotFoundException.class).when(service).consultarCep("94");

        assertThrows(ApiNotFoundException.class, () -> controller.getCep("94"));
    }
}
