package com.example.cepapi.service;

import com.example.cepapi.cep.service.CepService;
import com.example.cepapi.configuration.exception.ApiNotFoundException;
import com.example.cepapi.registrationPeople.model.pessoa.Pessoa;
import com.example.cepapi.registrationPeople.model.pessoa.response.PessoaResponse;
import com.example.cepapi.registrationPeople.repository.CadastroRepository;
import com.example.cepapi.registrationPeople.service.CadastroServices;
import com.example.cepapi.registrationPeople.service.WeatherService;
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

import static com.example.cepapi.controller.stub.PessoaControllerStub.createAEntity;
import static com.example.cepapi.controller.stub.PessoaControllerStub.createAResponse;
import static com.example.cepapi.registrationPeople.model.pessoa.mapper.PessoaMapper.optionalToEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class CadastroServicesTest {

    @InjectMocks //Usado para testar a classe em si
    private CadastroServices cadastroServices;
    @MockBean //Um mock substituirá o bean existente do mesmo tipo definido no ApplicationContext
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

        assertEquals(actual.isPresent(), expect.isPresent());
    }

    @Test
    @DisplayName("Retornar ApiNotFound quando ID não existe")
    void testFindByIdNotFound() {
        String id = "10";

        doThrow(ApiNotFoundException.class).when(cadastroRepository).findById(id);

        assertThrows(ApiNotFoundException.class,
                () -> cadastroServices.findById("1"));
    }

    @SuppressWarnings("SuspiciousMethodCalls")
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
        Pessoa pessoa = new Pessoa();

        doThrow(ApiNotFoundException.class)
                .when(cadastroRepository).findByNome(pessoa.getNome());

        assertThrows(ApiNotFoundException.class, () -> cadastroServices.findByNome(""));
    }

    @Test
    @DisplayName("Deve atualizar uma pessoa com sucesso")
    void shouldUpdateAPersonWithSuccess2() {
        String id = "1";
        Pessoa atualizada = createAEntity();

        when(cadastroRepository.findById(id)).thenReturn(optionalToEntity(atualizada));
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
    void updateInvalidPerson() {
        var pessoa = new Pessoa();

        doThrow(ApiNotFoundException.class).when(cadastroRepository).save(pessoa);

        assertThrows(ApiNotFoundException.class,
                () -> cadastroServices.update(null, pessoa));

        verify(cadastroRepository, never()).save(pessoa);
    }

    @Test
    @DisplayName("Deve criar uma pessoa com sucesso")
    void shouldCreateAPersonWithSuccess() {
        Pessoa pessoa = createAEntity();

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
    @DisplayName("Deve deletar todas as pessoas quando não passar nada")
    void shouldDeteAllPeopleWhenIdIsNull2() {
        assertDoesNotThrow( () -> cadastroServices.deletePeolpleByIDs(null));

        verify(cadastroRepository, times(1)).deleteAll();
    }

}
