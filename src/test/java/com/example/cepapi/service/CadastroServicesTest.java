package com.example.cepapi.service;

import com.example.cepapi.configuration.exception.ApiNotFoundException;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.repository.CadastroRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static com.example.cepapi.controller.PessoaControllerStub.createAEntity;
import static com.example.cepapi.controller.PessoaControllerStub.createAResponse;
import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.optionalToEntity;
import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.requestPessoa;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@RequiredArgsConstructor
public class CadastroServicesTest {

    @InjectMocks
    private CadastroServices cadastroServices;
    @MockBean
    CadastroRepository cadastroRepository;
    @Mock
    CepService cepService;
    @Mock
    WeatherService weatherService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Deve mostrar todas as pessoas")
    void shouldfindAllPeople() {
        List<PessoaResponse> expect = new ArrayList<>();

        doReturn(expect)
                .when(cadastroRepository).findAll();

        var actual = cadastroServices.findAll();

        assertEquals(expect, actual);

        verify(cadastroRepository, atLeastOnce()).findAll();

    }

    @Test
    @DisplayName("Deve procurar uma pessoa por ID")
    void shouldShowPersonByID() {
        String id = "1";
        Optional<Pessoa> expect = Optional.ofNullable(createAEntity());

        doReturn(expect)
                .when(cadastroRepository).findById(id);

        Optional<Pessoa> actual = Optional.ofNullable(cadastroServices.findById(id));

        assertEquals(expect.get().getId(), actual.get().getId());
        assertEquals(expect.get().getNome(), actual.get().getNome());
        assertEquals(expect.get().getDataDeNascimento(), actual.get().getDataDeNascimento());
    }

    @Test
    @DisplayName("Return ApiNotFound quando ID não existe")
    void testFindByIdNotFound() {
        Pessoa pessoa = new Pessoa();

        doThrow(ApiNotFoundException.class).when(cadastroRepository).findById(pessoa.getId());

        assertThrows(ApiNotFoundException.class,
                () -> cadastroServices.findById("1"));
    }

    @Test
    @DisplayName("Deve procurar uma pessoa por nome")
    void shouldShowPersonByName() {
        String nome = "Camila";
        List<PessoaResponse> expect = Collections.singletonList(createAResponse());

        doReturn(expect)
                .when(cadastroRepository).findByNome(nome);

        var actual = cadastroServices.findByNome(nome);

        assertNotNull(actual);
        assertEquals(expect.contains(nome), actual.contains(nome));
    }

    @Test
    @DisplayName("Não deve procurar uma pessoa por nome inexistente")
    void shouldNotShowPersonByName() {
        String nome = "1";

        doThrow(ApiNotFoundException.class)
                .when(cadastroRepository).findByNome(nome);

        assertThrows(ApiNotFoundException.class, () -> cadastroServices.findByNome(nome));

    }

    @Test
    @DisplayName("Deve atualizar uma pessoa com sucesso")
    void shouldUpdateAPersonWithSuccess2() {
        String id = "1";
        Pessoa atualizada = createAEntity();

        when(cadastroRepository.findById(any())).thenReturn(optionalToEntity(atualizada));
        when(cadastroRepository.save(any())).thenReturn(atualizada);

        var actual = cadastroServices.update(id, atualizada);
        cepService.pesquisarCepESalvarNoBanco(atualizada);
        weatherService.pesquisarTemperaturaESalvarNoBanco(atualizada);

        assertNotNull(actual);
        assertEquals(atualizada.getId(), actual.getId());
        assertEquals(atualizada.getNome(), actual.getNome());
        assertEquals(atualizada.getDataDeNascimento(), actual.getDataDeNascimento());
        assertEquals(atualizada.getEndereco(), actual.getEndereco());
    }

    @Test
    @DisplayName("Deve ocorrer erro ao tentar atualizar uma pessoa inexistente.")
    public void updateInvalidPerson() {
        String id = null;
        var pessoa = new Pessoa();

        doThrow(ApiNotFoundException.class).when(cadastroRepository).save(pessoa);

        assertThrows(ApiNotFoundException.class,
                () -> cadastroServices.update(id, pessoa));

        verify(cadastroRepository, never()).save(pessoa);
    }

    @Test
    @DisplayName("Deve criar uma pessoa com sucesso")
    void shouldCreateAPersonWithSuccess() {
        Pessoa pessoa = CepServiceTest.createAEntity();

        when(cadastroRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        var response = cadastroServices.create(pessoa);

        assertNotNull(response);
        assertEquals(pessoa.getId(), response.getId());
        assertEquals(pessoa.getNome(), response.getNome());
        assertEquals(pessoa.getDataDeNascimento(), response.getDataDeNascimento());
    }

    @Test
    @DisplayName("Não deve criar uma pessoa nula")
    void shouldNotCreateANullPerson() {
        Pessoa pessoa = createAEntity();

        doThrow(NullPointerException.class).when(cadastroRepository).save(pessoa);

        assertThrows(NullPointerException.class,
                () -> cadastroServices.create(pessoa));
    }

    @Test
    @DisplayName("Deve deletar pessoas por ids")
    void shouldDeletePersonByIds() {
        List<String> ids = Arrays.asList("1", "2");

        doNothing().when(cadastroRepository).deleteAllById(ids);

        cadastroServices.deletePeolpleByIDs(ids);

        verify(cadastroRepository).deleteAllById(ids);
    }

    @Test
    @DisplayName("Deve deletar todas as pessoas quando id for null")
    void shouldDeteAllPeopleWhenIdIsNull() {
        doNothing().when(cadastroRepository).deleteAll();

        cadastroServices.deletePeolpleByIDs(null);
    }
}
