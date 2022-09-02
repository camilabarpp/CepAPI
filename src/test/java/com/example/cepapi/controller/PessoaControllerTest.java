package com.example.cepapi.controller;

import com.example.cepapi.configuration.exception.ApiNotFoundException;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.mapper.PessoaMapper;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.repository.CadastroRepository;
import com.example.cepapi.service.CadastroServices;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.example.cepapi.controller.PessoaControllerStub.*;
import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.*;
import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
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
    @DisplayName("Deve procurar todas as pessoas")
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
    public void shouldCreateACookie() {
        Cookie cookies = new Cookie("Pessoa", "Camila");
        HttpServletResponse response = mock(HttpServletResponse.class);
        CadastroController controller = mock(CadastroController.class);

        response.addCookie(cookies);
        response.addHeader("Pessoa", "Camila");

        controller.criandoCoookie(response, "Camila");

        Mockito.verify( controller, atLeastOnce()).criandoCoookie(response, "Camila");
        assertThat(cookies).isNotNull();
        Mockito.verify( response ).addHeader( "Pessoa", "Camila");

    }

    @Test
    @DisplayName("Deve buscar todos os cookies")
    public void shouldGetACookie() {
        Cookie cookies = new Cookie("Pessoa", "Camila");
        HttpServletRequest request = mock(HttpServletRequest.class);
        CadastroController controller = mock(CadastroController.class);

        controller.readAllCookies(request);
        Mockito.verify( controller, atLeastOnce()).readAllCookies(request);
        assertThat(cookies).isNotNull();
    }
}

