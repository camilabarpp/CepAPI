package com.example.cepapi.configuration.exception;

import com.example.cepapi.configuration.exception.errorobject.ErrorObject;
import com.example.cepapi.configuration.exception.errorresponse.ErrorResponse;
import com.example.cepapi.integration.resttemplate.weather.IntegrationWeather;
import com.example.cepapi.service.CadastroServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(value = ApiExceptionHandlerTest.class)
@AutoConfigureMockMvc
class ApiExceptionHandlerTest {
    @InjectMocks
    private ApiExceptionHandler exceptionHandler;
    @Mock
    ErrorObject errorObject;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Deve lançar ApiNotFoundException")
    void apiNotFoundException() {
        ErrorResponse response = exceptionHandler
                .apiNotFoundException(new ApiNotFoundException(
                        errorObject.getField(),
                        errorObject.getParameter()));

        assertNotNull(response);
        assertThat(response.getError().equals(errorObject));
        assertNotNull(response.getTimestamp(), String.valueOf(LocalDateTime.now()));
    }

    @Test
    @DisplayName("Deve lançar NullPointerException")
    void nullPointerException() {
        ErrorResponse response = exceptionHandler
                .nullPointerException(new NullPointerException(
                        errorObject.getParameter()));

        assertNotNull(response);
        assertThat(response.getError().equals(errorObject));
        assertNotNull(response.getTimestamp(), String.valueOf(LocalDateTime.now()));
    }

    @Test
    @DisplayName("Deve lançar HttpMessageNotReadableException")
    void httpMessageNotReadableException() {
        ErrorResponse response = exceptionHandler
                .httpMessageNotReadableException(new HttpMessageNotReadableException(
                        errorObject.getParameter()));

        assertNotNull(response);
        assertThat(errorObject.equals(response.getError()));
        assertNotNull(response.getTimestamp(), String.valueOf(LocalDateTime.now()));
    }

    @Test
    @DisplayName("Deve lançar HttpRequestMethodNotSupportedException")
    void shouldThowsHttpRequestMethodNotSupportedException() {
        ErrorResponse response = exceptionHandler
                .methodArgumentNotValidException(new HttpRequestMethodNotSupportedException(
                        errorObject.getParameter()));
        assertNotNull(response);
        assertThat(response.getError().equals(errorObject));
        assertNotNull(response.getTimestamp(), String.valueOf(LocalDateTime.now()));
    }

    @Test
    @DisplayName("Deve lançar ResponseStatusException")
    void shouldThowsResponseStatusException() {
        ErrorResponse response = exceptionHandler
                .handleException(new ResponseStatusException(
                        HttpStatus.BAD_REQUEST));

        assertNotNull(response);
        assertThat(response.getError().equals(errorObject));
        assertNotNull(response.getTimestamp(), String.valueOf(LocalDateTime.now()));
    }

    @Test
    @DisplayName("Deve lançar HttpClientErrorException")
    void shouldThowsHttpClientErrorException() {
        ErrorResponse response = exceptionHandler
                .httpClientErrorException(new HttpClientErrorException(
                        HttpStatus.BAD_REQUEST
                ));

        assertNotNull(response);
        assertThat(response.getError().equals(errorObject));
        assertNotNull(response.getTimestamp(), String.valueOf(LocalDateTime.now()));
    }

    @Test
    @DisplayName("Deve lançar Exception")
    void shouldThowsException() {
        ErrorResponse response = exceptionHandler
                .handleException(new Exception(
                        errorObject.getParameter()));
        assertNotNull(response);
        assertThat(response.getError().equals(errorObject));
        assertNotNull(response.getTimestamp(), String.valueOf(LocalDateTime.now()));
    }
}