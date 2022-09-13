package com.example.cepapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class CepApiApplicationTests {

    @Test
    @DisplayName("Teste com a classe principal")
    void contextLoads() {
        CepApiApplication.main(new String[] {});
        assertDoesNotThrow(this::doNotThrowException);
    }

    private void doNotThrowException(){
    }

}
