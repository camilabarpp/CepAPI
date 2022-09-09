package com.example.cepapi.controller;

import com.example.cepapi.configuration.exception.ApiNotFoundException;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.service.CadastroServices;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.cepapi.controller.stub.PessoaControllerStub.*;
import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.requestPessoa;
import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.toRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {
    @InjectMocks
    private CadastroController pessoaController;
    @Mock
    private CadastroServices cadastroServices;

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Deve mostrar todas as pessoas")
    void shouldShowAllPeople() {
        String nome = null;
        List<PessoaResponse> expect = new ArrayList<>();

        doReturn(expect)
                .when(this.cadastroServices).findAll();

        List<PessoaResponse> actual = this.pessoaController.findByNome(nome);

        assertEquals(expect, actual);
    }


    @Test
    @DisplayName("Deve procurar todas as pessoas por nome")
    void shouldShowAllPeopleByName() {
        String name = "Camila";
        List<PessoaResponse> expect = new ArrayList<>();

        doReturn(expect)
                .when(this.cadastroServices).findByNome(name);

        List<PessoaResponse> actual = this.pessoaController.findByNome(name);
        assertEquals(expect, actual);

        verify(this.cadastroServices, atLeastOnce()).findByNome(name);

    }

    @Test
    @DisplayName("Deve procurar todas as pessoas por nome")
    void shouldShowAllPeopleByName2() {
        String name = "1";

        doThrow(ApiNotFoundException.class)
                .when(this.cadastroServices).findByNome(name);

        assertThrows(ApiNotFoundException.class,() -> cadastroServices.findByNome(name));
    }

    @Test
    @DisplayName("Deve procurar uma pessoa pelo id com sucesso.")
    void shouldShowEmployeeByID() {

        String id = "1";
        Pessoa expect = createAEntity();

        doReturn(expect)
                .when(this.cadastroServices).findById(id);

        var actual = this.pessoaController.findById(id);

        assertEquals(expect.getId(), actual.getId());
        assertEquals(expect.getNome(), actual.getNome());
        assertEquals(expect.getDataDeNascimento(), actual.getDataDeNascimento());
        assertEquals(expect.getEndereco(), actual.getEndereco());
        assertEquals(expect.getTemperatura(), actual.getTemperatura());

        verify(this.cadastroServices, atLeastOnce()).findById(id);
    }

    @Test
    @DisplayName("Deve lancar excessão ao tentar pesquisar pessoa com id inválido")
    void shouldNotShowEmployeeByInvalidId() throws Exception {
        String id = null;

        doThrow(ApiNotFoundException.class)
                .when(this.cadastroServices).findById(id);

        assertThrows(ApiNotFoundException.class, () -> this.cadastroServices.findById(id));
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

        verify(this.cadastroServices,  atLeastOnce()).create(requestPessoa(request));
    }

    @Test
    @DisplayName("Não deve criar uma pessoa nula")
    void shouldNotCreateANullPerson() {
        CadastroController controller = mock(CadastroController.class);
        CadastroServices services = mock(CadastroServices.class);

        PessoaRequest expect = new PessoaRequest();

        when(controller.create(any())).thenReturn(toRequest(expect));

        assertNull(expect.getId());
        assertNull(expect.getNome());
        assertNull(expect.getDataDeNascimento());
        assertNull(expect.getEndereco());
        assertNull(expect.getTemperatura());
        verify(services, never()).create(requestPessoa(expect));

    }

    @Test
    @DisplayName("Deve alterar uma pessoa pelo id com sucesso.")
    void shouldChangeAPersonByID() {
        String id = "1";
        Pessoa pessoa = createAEntity();
        PessoaRequest request = createARequest();
        when(cadastroServices.update(any(),any())).thenReturn(pessoa);

        var response = pessoaController.update(id, request);

        assertNotNull(response);
        assertEquals(pessoa.getId(), response.getId());
        assertEquals(pessoa.getNome(), response.getNome());
        assertEquals(pessoa.getDataDeNascimento(), response.getDataDeNascimento());
        assertEquals(pessoa.getEndereco(), response.getEndereco());

        verify(this.cadastroServices,  atLeastOnce()).update(id, requestPessoa(request));

    }

    @Test
    @DisplayName("Não deve atualizar pessoa quando o id for nulo")
    void shouldNotUpdatePersonWhenIdIsNull() {
        String id = null;
        Pessoa pessoa = createAEntityNull();

        doThrow(ApiNotFoundException.class)
                .when(this.cadastroServices).update(id, pessoa);

        assertThrows(ApiNotFoundException.class, () -> this.cadastroServices.update(id, pessoa));
    }

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

    @Test
    @DisplayName("Deve criar um cookie")
    public void shouldCreateACookie() throws Exception {
        Cookie cookie = new Cookie("A", "B");

        mvc.perform(MockMvcRequestBuilders.post("/v1/api/Camila")
                        .cookie(cookie)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve buscar todos os cookies")
    public void shouldGetACookie() throws Exception {
        Cookie cookie = new Cookie("A", "V");

        mvc.perform(get("/v1/api/cookies/get")
                        .cookie(cookie)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve testar se o cookie existe")
    public void testNotExists() throws Exception {
        mvc.perform(get("/v1/api/cookies/get"))
                .andExpect(cookie().doesNotExist("unknownCookie"));

    }
}

