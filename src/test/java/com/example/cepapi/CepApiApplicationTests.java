package com.example.cepapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CepApiApplicationTests {

    @Test
    @DisplayName("Teste com a classe principal")
    void contextLoads() {
        CepApiApplication.main(new String[] {});
    }

}
