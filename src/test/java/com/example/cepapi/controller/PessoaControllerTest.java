package com.example.cepapi.controller;

import com.example.cepapi.configuration.exception.ApiNotFoundException;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.service.CadastroServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.cepapi.controller.PessoaControllerStub.*;
import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.requestPessoa;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PessoaControllerTest {

    @InjectMocks
    private CadastroController pessoaController;
    @Mock
    private CadastroServices cadastroServices;

    @Test
    @DisplayName("Deve procurar todas as pessoas")
    void shouldShowAllPeople() {
        List<PessoaResponse> expect = new ArrayList<>();

        doReturn(expect)
                .when(this.cadastroServices).findAll();

        List<PessoaResponse> actual = this.pessoaController.findAll();
        assertEquals(expect, actual);

        verify(this.cadastroServices, atLeastOnce()).findAll();

    }

    @Test
    @DisplayName("Deve procurar uma pessoa pelo id com sucesso.")
    void shouldShowEmployeeByID() {

        String id = "1";
        PessoaResponse expect = createAResponse();

        doReturn(expect)
                .when(this.cadastroServices).findById(id);

        PessoaResponse actual = this.pessoaController.findById(id);

        assertEquals(expect.getId(), actual.getId());
        assertEquals(expect.getNome(), actual.getNome());
        assertEquals(expect.getDataDeNascimento(), actual.getDataDeNascimento());
        assertEquals(expect.getEndereco(), actual.getEndereco());

        verify(this.cadastroServices, atLeastOnce()).findById(id);
    }

    @Test
    void shouldNotShowEmployeeByInvalidId() throws Exception {
        String id = null;


        doThrow(ApiNotFoundException.class)
                .when(this.cadastroServices).findById(id);

        Assertions.assertThrows(ApiNotFoundException.class, () -> this.cadastroServices.findById(id));
    }

    @Test
    @DisplayName("Deve criar uma pessoa com sucesso.")
    void shouldRegisterAPerson() throws Exception {
        Pessoa pessoa = createAEntity();
        PessoaRequest request = createARequest();
        when(cadastroServices.create(any())).thenReturn(pessoa);

        var response = pessoaController.create(request);

        assertEquals(pessoa.getId(), response.getId());
        assertEquals(pessoa.getNome(), response.getNome());
        assertEquals(pessoa.getDataDeNascimento(), response.getDataDeNascimento());
        assertEquals(pessoa.getEndereco(), response.getEndereco());
    }

    @Test
    @DisplayName("Deve alterar um funcionario pelo id com sucesso.")
    void shouldChangeEmployeeByID() {
        String id = "1";
        Pessoa pessoa = createAEntity();
        PessoaResponse expect = createAResponse();
        PessoaRequest request = createARequest();

        doReturn(expect)
                .when(this.cadastroServices).update(id, pessoa);

        var actual = this.pessoaController.update(id, request);

        //assertEquals(expect.getId(), actual.getId());

        verify(this.cadastroServices, atLeastOnce()).update(id, pessoa);
    }

    @Test
    @DisplayName("Deve alterar um funcionario pelo id com sucesso.")
    void shouldChangeEmployeeByID2() {
        String id = "1";
        Pessoa pessoa = createAEntity();
        PessoaResponse expect = createAResponse();
        PessoaRequest request = createARequest();

        when(cadastroServices.update(id, pessoa)).thenReturn(expect);

        var response = pessoaController.update(id, request);

        //assertNotNull(response);
        assertEquals(pessoa.getId(), response.getId());
        assertEquals(pessoa.getNome(), response.getNome());
        assertEquals(pessoa.getDataDeNascimento(), response.getDataDeNascimento());
        assertEquals(pessoa.getEndereco(), response.getEndereco());    }

    @Test
    @DisplayName("Deve deletar um funcionario pelo id com sucesso.")
    void shouldDeleteEmployeeByID() {

        String id = "1";

        doNothing()
                .when(this.cadastroServices).deletePeolpleByIDs(Collections.singletonList(id));

        this.pessoaController.deletePeolpleByIDs(Collections.singletonList(id));

        verify(this.cadastroServices, atLeastOnce()).deletePeolpleByIDs(Collections.singletonList(id));
    }

    @Test
    @DisplayName("Deve deletar um ou mais funcionarios pelos ids com sucesso.")
    void shouldDeleteEmployeesByIDs() {

        List<String> ids = new ArrayList<>();

        doNothing()
                .when(this.cadastroServices).deletePeolpleByIDs(ids);

        this.pessoaController.deletePeolpleByIDs(ids);

        verify(this.cadastroServices, atLeastOnce()).deletePeolpleByIDs(ids);
    }
}
