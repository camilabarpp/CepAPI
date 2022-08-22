package com.example.cepapi.service;

import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.repository.CadastroRepository;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroServicesTest {

    @InjectMocks
    private CadastroServices service;

    @Mock
    CadastroRepository repository;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    @DisplayName("Deve criar uma pessoa")
    void shouldCreateAPerson() {
        Pessoa pessoa = createAEntity();
        PessoaRequest request = createARequest();

        doReturn(pessoa).when(this.repository).save(any(Pessoa.class));
        PessoaResponse atual = this.service.create(request);
        assertEquals(pessoa.getNome(), atual.getNome());
        verify(this.repository, atLeastOnce()).save(any(Pessoa.class));
    }

    @Test
    void delete() {
    }

    @Test
    void deletePeolpleByIDs() {
    }

    @Test
    void deleteAll() {
    }

    private static Pessoa createAEntity() {
        return Pessoa.builder()
                .id("1")
                .nome("Camila")
                .dataDeNascimento("02/07/1996")
                .cep("94020070")
                .logradouro("Rua João Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build();
    }

    private static PessoaRequest createARequest() {
        return PessoaRequest.builder()
                .id("1")
                .nome("Camila")
                .dataDeNascimento("02/07/1996")
                .cep("94020070")
                .logradouro("Rua João Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build();
    }
}