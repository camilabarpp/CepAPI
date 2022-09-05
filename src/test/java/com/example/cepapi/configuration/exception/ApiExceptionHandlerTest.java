package com.example.cepapi.configuration.exception;

import com.example.cepapi.configuration.exception.errorresponse.ErrorResponse;
import com.example.cepapi.integration.resttemplate.weather.IntegrationWeather;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.service.CadastroServices;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;

import static com.example.cepapi.controller.PessoaControllerStub.createAEntityNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.doThrow;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(value = ApiExceptionHandlerTest.class)
@AutoConfigureMockMvc
class ApiExceptionHandlerTest {

    @MockBean
    private CadastroServices services;

    @Mock
    private IntegrationWeather integrationWeather;

    @Test
    @DisplayName("Deve lançar NullPointerException")
    void nullPointerException() {

        Pessoa pessoa = createAEntityNull();

        doThrow(NullPointerException.class)
                .when(this.services).create(pessoa);

        assertThrows(NullPointerException.class, () -> this.services.create(pessoa));    }

    @Test
    @DisplayName("Deve lançar HttpMessageNotReadableException")
    void httpMessageNotReadableException() {
        Pessoa pessoa = Pessoa.builder().nome("").build();

        doThrow(HttpMessageNotReadableException.class)
                .when(this.services).create(pessoa);

        assertThrows(HttpMessageNotReadableException.class, () -> this.services.create(pessoa));
    }

    @Test
    @DisplayName("Deve lançar HttpClientErrorException")
    void handleException() {
        doThrow(HttpClientErrorException.class)
                .when(this.integrationWeather).getWeather(" ");

        assertThrows(HttpClientErrorException.class, () -> this.integrationWeather.getWeather(" "));
    }

    @Test
    @DisplayName("Deve lançar ApiNotFoundException")
    void apiNotFoundException() {
        String id = "10";

        doThrow(ApiNotFoundException.class)
                .when(this.services).findById(id);

        assertThrows(ApiNotFoundException.class, () -> this.services.findById(id));
    }
}