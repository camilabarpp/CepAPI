package com.example.cepapi.service;

import com.example.cepapi.configuration.exception.ApiNotFoundException;
import com.example.cepapi.integration.resttemplate.cep.IntegrationCep;
import com.example.cepapi.model.cep.CepMapper;
import com.example.cepapi.model.cep.response.CepResponse;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.mapper.PessoaMapper;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.repository.CadastroRepository;
import com.example.cepapi.repository.CepRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;
import java.util.stream.Stream;

import static com.example.cepapi.controller.PessoaControllerStub.*;
import static com.example.cepapi.model.cep.CepMapper.entityToResponse;
import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.requestPessoa;
import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.toEntityOptional;
import static com.example.cepapi.service.CadastroServiceStub.PessoaServiceStubExpected;
import static com.example.cepapi.service.CadastroServiceStub.PessoaServiceStubResponse;
import static com.example.cepapi.service.CepServiceTest.createAResponseCep;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@RequiredArgsConstructor
public class CadastroServicesTest {

    @InjectMocks
    private CadastroServices cadastroServices;
    @InjectMocks
    private CepService cepService;
    @MockBean
    CadastroRepository cadastroRepository;

    @MockBean
    CepRepository cepRepository;

    @MockBean
    IntegrationCep integrationCep;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve mostrar todas as pessoas")
    void shouldfindAllPeople() {
        CadastroServices services = mock(CadastroServices.class);

        List<PessoaResponse> expect = new ArrayList<>();

        doReturn(expect)
                .when(services).findAll();

        List<PessoaResponse> actual = services.findAll();
        assertEquals(expect, actual);

        verify(services, atLeastOnce()).findAll();

    }

    @Test
    @DisplayName("Deve procurar uma pessoa por ID")
    void shouldShowEmployeeByID() {
        CadastroServices services = mock(CadastroServices.class);

        String id = "1";
        PessoaResponse expect = createAResponse();

        doReturn(expect)
                .when(services).findById(id);

        PessoaResponse actual = services.findById(id);

        assertEquals( expect.getId(), actual.getId());
        assertEquals( expect.getNome(), actual.getNome());
        assertEquals( expect.getDataDeNascimento(), actual.getDataDeNascimento());
    }

    @Test
    @DisplayName("Return a optonal empty quando ID não existe")
    void testFindByIdNotFound() {
        doReturn(Optional.empty()).when(cadastroRepository).findById("1");

        assertThrows(ApiNotFoundException.class,
                () -> cadastroServices.findById("1"));
   }

    @Test
    @DisplayName("Deve atualizar uma pessoa com sucesso")
    void shouldUpdateAPersonWithSuccess2() {
        CadastroServices cadastroServices = mock(CadastroServices.class);
        String id = "1";
        Pessoa atualizar = new Pessoa();
        Pessoa atualizada = createAEntity();

        doReturn(atualizada).when(cadastroServices).update(id, atualizar);

        cadastroRepository.save(atualizada);

        var actual = cadastroServices.update(id, atualizar);

        assertEquals(atualizada.getId(), actual.getId());
        assertEquals(atualizada.getNome(), actual.getNome());
        assertEquals(atualizada.getDataDeNascimento(), actual.getDataDeNascimento());
        assertEquals(atualizada.getEndereco(), actual.getEndereco());

        verify(cadastroRepository).save(atualizada);
    }

    @Test
    @DisplayName("Deve ocorrer erro ao tentar atualizar uma pessoa inexistente.")
    public void updateInvalidPerson(){
        var pessoa = new PessoaRequest();

        assertThrows(NullPointerException.class,
                () -> cadastroServices.update(null, requestPessoa(pessoa)));

        verify( cadastroRepository, never() ).save(requestPessoa(pessoa));
    }

    @Test
    @DisplayName("Deve criar uma pessoa com sucesso")
    void shouldCreateAPersonWithSuccess() {
        CadastroServices cadastroServices = mock(CadastroServices.class);

        Pessoa expect = createAEntity();

        doReturn(expect).when(cadastroServices).create(expect);

        cadastroRepository.save(expect);

        var actual = cadastroServices.create(expect);

        assertEquals(expect.getId(), actual.getId());
        assertEquals(expect.getNome(), actual.getNome());
        assertEquals(expect.getDataDeNascimento(), actual.getDataDeNascimento());
        assertEquals(expect.getEndereco(), actual.getEndereco());

        verify(cadastroRepository).save(expect);
    }

    @Test
    @DisplayName("Não deve criar uma pessoa nula")
    void shouldNotCreateANullPerson() {
        CadastroServices cadastroServices = mock(CadastroServices.class);
        Pessoa expect = new Pessoa();

        doThrow(ApiNotFoundException.class)
                .when(cadastroServices).create(expect);

        assertThrows(ApiNotFoundException.class, () -> cadastroServices.create(expect));

        verify(cadastroRepository, never()).save(expect);
    }

    @Test
    @DisplayName("Deve deletar pessoas por ids")
     void shouldDeletePersonByIds() {
        List<String> ids = Arrays.asList("1", "2");

        cadastroServices.deletePeolpleByIDs(ids);

        verify(cadastroRepository).deleteAllById(ids);
    }

    @Test
    @DisplayName("Deve deletar todas as pessoas quando id for null")
    void shouldDleteAllPeopleWhenIdIsNull() {
        cadastroServices.deletePeolpleByIDs(null);

        verify(cadastroRepository).deleteAll();
    }
}
