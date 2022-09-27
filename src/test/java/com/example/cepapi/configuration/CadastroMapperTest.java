package com.example.cepapi.configuration;

import com.example.cepapi.model.cep.CepMapper;
import com.example.cepapi.model.pessoa.mapper.PessoaMapper;
import com.example.cepapi.model.weather.mapper.WeatherMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(value = CadastroMapperTest.class)
@AutoConfigureMockMvc
class CadastroMapperTest {
    @BeforeEach
    void setUp() {
        openMocks(this);
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
