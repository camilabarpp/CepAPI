package com.example.cepapi.integration.weather;

import com.example.cepapi.configuration.exception.ErroHandler;
import com.example.cepapi.integration.resttemplate.weather.IntegrationWeather;
import com.example.cepapi.model.weather.response.WeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static com.example.cepapi.integration.weather.WeatherIntegrationStub.weatherIntegrationResponse;
import static com.example.cepapi.integration.weather.WeatherIntegrationStub.weatherIntegrationResponseExpect;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = IntegrationWeather.class)
@EnableWebMvc
@AutoConfigureMockMvc
class WeatherIntegrationTest {

    @InjectMocks
    private IntegrationWeather integrationWeather;

    private static ClientAndServer server;

    @BeforeAll
    static void startServer(){
        server = startClientAndServer();
    }

    @BeforeEach
    void setupClass() {
        RestTemplate restTemplate = new RestTemplateBuilder()
                    .rootUri("https://weather-by-api-ninjas.p.rapidapi.com")
                    .defaultHeader("X-RapidAPI-Key", "3c2316e5ccmsh4c9334453d4100cp11f529jsn398286bc960e")
                    .defaultHeader("X-RapidAPI-Host", "weather-by-api-ninjas.p.rapidapi.com")
                    .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                    .errorHandler(new ErroHandler())
                    .build();
        integrationWeather = new IntegrationWeather(restTemplate);
    }

    @AfterAll
    static void stopServer(){
        server.close();
    }

    @Test
    void whenFindCepReturnCepIntegration() throws JsonProcessingException {

        WeatherResponse expect = weatherIntegrationResponseExpect();

        HttpRequest request =  HttpRequest.request()
                .withPath("http://localhost:8080/v1/api/city/?city=Canoas")
                .withMethod("GET");


        ObjectMapper mapper = new ObjectMapper();

        var body = mapper.writeValueAsString(weatherIntegrationResponse());

        HttpResponse response = HttpResponse.response(body)
                .withStatusCode(HttpStatus.OK.value())
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        server.when(request).respond(response);

        var actual = integrationWeather.getWeather("Canoas");
        assertEquals(expect, actual);
    }
}
