package com.example.cepapi.integration.weather;

import com.example.cepapi.configuration.exception.ApiNotFoundException;
import com.example.cepapi.configuration.exception.ErroHandler;
import com.example.cepapi.configuration.resttemplate.weather.IntegrationWeather;
import com.example.cepapi.registrationPeople.model.weather.response.WeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import org.junit.jupiter.api.*;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
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
    @DisplayName("Deve pesquisar temperatura na api externa com sucesso")
    void whenFindCepReturnCepIntegration() throws JsonProcessingException {

        WeatherResponse expect = weatherIntegrationResponseExpect();

        HttpRequest request =  HttpRequest.request()
                .withPath("v1/weather?city=Canoas")
                .withMethod("GET");

        ObjectMapper mapper = new ObjectMapper();

        var body = mapper.writeValueAsString(weatherIntegrationResponse());

        HttpResponse response = HttpResponse.response(body)
                .withStatusCode(HttpStatus.OK.value())
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        server.when(request).respond(response);

        var actual = integrationWeather.getWeather("Canoas");
        assertEquals(expect.getTemp(), actual.getTemp());
        assertEquals(expect.getFeelsLike(), actual.getFeelsLike());
        assertEquals(expect.getMaxTemp(), actual.getMaxTemp());
        assertEquals(expect.getMinTemp(), actual.getMinTemp());
    }

    @Test
    @DisplayName("Deve lançar ApiNotFoundException quando a cidade estiver em branco")
    void handleApiRequestExceptionNotFound() {
        IntegrationWeather integration = mock(IntegrationWeather.class);

        doThrow(ApiNotFoundException.class)
                .when(integration).getWeather(null);
        assertThrows(ApiNotFoundException.class, () -> integration.getWeather(null));
    }
}
