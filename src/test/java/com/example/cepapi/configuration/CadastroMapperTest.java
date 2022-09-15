package com.example.cepapi.configuration;

import com.example.cepapi.configuration.exception.ApiExceptionHandler;
import com.example.cepapi.configuration.exception.ApiNotFoundException;
import com.example.cepapi.configuration.exception.errorresponse.ErrorResponse;
import com.example.cepapi.model.cep.CepMapper;
import com.example.cepapi.model.pessoa.mapper.PessoaMapper;
import com.example.cepapi.model.weather.mapper.WeatherMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(value = CadastroMapperTest.class)
@AutoConfigureMockMvc
class CadastroMapperTest {
    @InjectMocks
    private ApiExceptionHandler exceptionHandler;
    @Mock
    private ErrorResponse errorObject;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Deve lançar ApiNotFoundException")
    void apiNotFoundException() {
        ErrorResponse response = exceptionHandler
                .apiNotFoundException(new ApiNotFoundException(
                        errorObject.getMessage()));

        assertNotNull(response);
        assertEquals("ApiNotFoundException", response.getParameter());
        assertEquals("NOT_FOUND", response.getField());
        assertEquals("Dados não encontrados!", response.getMessage());
        assertNotNull(response.getTimestamp());
    }

    @Test
    @DisplayName("Deve lançar NullPointerException")
    void nullPointerException() {
        ErrorResponse response = exceptionHandler
                .nullPointerException(new NullPointerException(
                        errorObject.getParameter()));

        assertNotNull(response);
        assertEquals("NullPointerException", response.getParameter());
        assertEquals("INTERNAL_SERVER_ERROR", response.getField());
    }
    @Test
    @DisplayName("Deve lançar HttpRequestMethodNotSupportedException")
    void shouldThowsHttpRequestMethodNotSupportedException() {
        ErrorResponse response = exceptionHandler
                .methodArgumentNotValidException(new HttpRequestMethodNotSupportedException(
                        errorObject.getParameter()));
        assertNotNull(response);
        assertEquals("HttpRequestMethodNotSupportedException", response.getParameter());
        assertEquals("METHOD_NOT_ALLOWED", response.getField());
    }

    @Test
    @DisplayName("Deve lançar ResponseStatusException")
    void shouldThowsResponseStatusException() {
        ErrorResponse response = exceptionHandler
                .handleException(new ResponseStatusException(
                        HttpStatus.BAD_REQUEST));

        assertNotNull(response);
        assertEquals("ResponseStatusException", response.getParameter());
        assertEquals("BAD_REQUEST", response.getField());
    }

    @Test
    @DisplayName("Deve lançar HttpClientErrorException")
    void shouldThowsHttpClientErrorException() {
        ErrorResponse response = exceptionHandler
                .httpClientErrorException(new HttpClientErrorException(
                        HttpStatus.BAD_REQUEST
                ));

        assertNotNull(response);
        assertEquals("HttpClientErrorException", response.getParameter());
        assertEquals("BAD_REQUEST", response.getField());
    }

    @Test
    @DisplayName("Deve lançar Exception")
    void shouldThowsException() {
        ErrorResponse response = exceptionHandler
                .handleException(new Exception(
                        errorObject.getParameter()));
        assertNotNull(response);
        assertEquals("Exception", response.getParameter());
        assertEquals("INTERNAL_SERVER_ERROR", response.getField());
    }


    @Test
    void weatherMapperTest_cannot_instantiate() {
        assertThrows(InvocationTargetException.class, () -> {
            var constructor = WeatherMapper.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }

    @Test
    void pessoaMapperTest_cannot_instantiate() {
        assertThrows(InvocationTargetException.class, () -> {
            var constructor = PessoaMapper.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }

    @Test
    void cepMapperTest_cannot_instantiate() {
        assertThrows(InvocationTargetException.class, () -> {
            var constructor = CepMapper.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }
}
