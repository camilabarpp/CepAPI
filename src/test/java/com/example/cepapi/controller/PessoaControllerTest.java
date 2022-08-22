package com.example.cepapi.controller;

import com.example.cepapi.service.CadastroServices;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Rule;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PessoaControllerTest {

    @InjectMocks
    private CadastroController pessoaController;

    @Mock
    private CadastroServices cadastroServices;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
/*
    @Test
    @DisplayName("Deve procurar todas as pessoas")
    void shouldShowAllPeople() {
        List<PessoaResponse> expect = new ArrayList<>();

        doReturn(expect)
                .when(this.cadastroServices).findAll();

        List<PessoaResponse> actual = this.pessoaController.findAll();
        assertEquals(expect, actual);

        Mockito.verify(this.cadastroServices, Mockito.atLeastOnce()).findAll();

    }

    @Test
    @DisplayName("Deve procurar uma pessoa pelo id com sucesso.")
    void shouldShowEmployeeByID() {

        String id = "1";

        PessoaResponse expect = PessoaResponse.builder()
                .id("1")
                .nome("Camila Barpp")
                .dataDeNascimento("1996-07-02")
                .cep("94020070")
                .logradouro("Rua Botijá")
                .numero("01")
                .bairro("Miraguaia")
                .localidade("Gravataí")
                .build();

        doReturn(expect)
                .when(this.cadastroServices).findById(id);

        PessoaResponse actual = this.pessoaController.findById(id);

        assertEquals(expect.getId(), actual.getId());
        assertEquals(expect.getNome(), actual.getNome());



        Mockito.verify(this.cadastroServices, Mockito.atLeastOnce()).findById(id);
    }

    @Test
    void shouldNotShowEmployeeByInvalidId() throws Exception {
        String id = null;


        doThrow(ApiNotFoundException.class)
                .when(this.cadastroServices).findById(id);

        Assertions.assertThrows(ApiNotFoundException.class, () -> this.cadastroServices.findById(id));
    }


    @Test
    @DisplayName("Deve criar um funcionario com sucesso.")
    void shouldRegisterEmployee() throws Exception {

        PessoaResponse expect = PessoaResponse.builder()
                .id("1")
                .nome("Camila Barpp")
                .dataDeNascimento("1996-07-02")
                .cep("94020070")
                .logradouro("Rua Botijá")
                .numero("01")
                .bairro("Miraguaia")
                .localidade("Gravataí")
                .build();

        PessoaRequest request = PessoaRequest.builder()
                .nome("Camila Barpp")
                .dataDeNascimento("1996-07-02")
                .cep("94020070")
                .logradouro("Rua Botijá")
                .numero("01")
                .bairro("Miraguaia")
                .localidade("Gravataí")
                .build();

        doReturn(expect)
                .when(this.cadastroServices).createPerson(request);

        PessoaResponse actual = this.pessoaController.create(request, expect.getCep());

        assertEquals(expect.getNome(), actual.getNome());

        Mockito.verify(this.cadastroServices, Mockito.atLeastOnce()).createPerson(request);

    }

    @Test
    @DisplayName("Deve alterar um funcionario pelo id com sucesso.")
    void shouldChangeEmployeeByID() {

        String id = "1";

        PessoaResponse expect = PessoaResponse.builder()
                .id("1")
                .nome("Camila Barpp")
                .dataDeNascimento("1996-07-02")
                .cep("94020070")
                .logradouro("Rua Botijá")
                .numero("01")
                .bairro("Miraguaia")
                .localidade("Gravataí")
                .build();

        PessoaRequest request = PessoaRequest.builder()
                .nome("Camila Barpp")
                .dataDeNascimento("1996-07-02")
                .cep("94020070")
                .logradouro("Rua Botijá")
                .numero("01")
                .bairro("Miraguaia")
                .localidade("Gravataí")
                .build();

        doReturn(expect)
                .when(this.cadastroServices).update(request, id);

        PessoaResponse actual = this.pessoaController.update(request, id);

        assertEquals(expect.getId(), actual.getId());

        Mockito.verify(this.cadastroServices, Mockito.atLeastOnce()).update(request, id);
    }

    @Test
    @DisplayName("Deve deletar um funcionario pelo id com sucesso.")
    void shouldDeleteEmployeeByID() {

        String id = "1";

        doNothing()
                .when(this.cadastroServices).delete(id);

        this.pessoaController.delete(id);

        Mockito.verify(this.cadastroServices, Mockito.atLeastOnce()).delete(id);
    }

    @Test
    @DisplayName("Deve deletar um ou mais funcionarios pelos ids com sucesso.")
    void shouldDeleteEmployeesByIDs() {

        List<String> ids = new ArrayList<>();

        doNothing()
                .when(this.cadastroServices).deletePeolpleByIDs(ids);

        this.pessoaController.deletePeolpleByIDs(ids);

        Mockito.verify(this.cadastroServices, Mockito.atLeastOnce()).deletePeolpleByIDs(ids);
    }*/
}
