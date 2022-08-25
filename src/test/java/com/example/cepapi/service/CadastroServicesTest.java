package com.example.cepapi.service;

import com.example.cepapi.configuration.ApiNotFoundException;
import com.example.cepapi.integration.resttemplate.cep.IntegrationCep;
import com.example.cepapi.model.cep.CepEntity;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.repository.CadastroRepository;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.*;
import static com.example.cepapi.service.CadastroServiceStub.PessoaServiceStubExpected;
import static com.example.cepapi.service.CadastroServiceStub.PessoaServiceStubResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class CadastroServicesTest {

    @Mock
    private CadastroServices cadastroServices;
    @MockBean
    CadastroRepository cadastroRepository;

    @Mock
    IntegrationCep integration;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @BeforeEach
    void setUp() {
        //autoCloseable = MockitoAnnotations.openMocks(this);
        this.cadastroServices = new CadastroServices();
    }

    @Test
    void findAll() {
        cadastroServices.findAll();
        verify(cadastroRepository).findAll();
    }

    @Test
    void findById() {
        String id = "1";

        when(cadastroRepository.findById(id)).thenReturn(Optional.of(createAEntity()));

        Optional<PessoaResponse> foundBook = Optional.ofNullable(cadastroServices.findById(id));

        assertThat( foundBook.isPresent() ).isTrue();

    }

    @Test
    void update() {
        String id = "1";

        PessoaRequest pessoaAtualizar = PessoaRequest.builder().id(id).build();

        PessoaRequest pessoaAtualizada = createARequest();
        pessoaAtualizada.setId(id);
        when(cadastroRepository.save(requestPessoa(pessoaAtualizar)))
                .thenReturn(requestPessoa(pessoaAtualizada));

        var pessoa = cadastroServices.save(pessoaAtualizar);

        assertThat(pessoa.getId()).isEqualTo(pessoaAtualizada.getId());
        assertThat(pessoa.getNome()).isEqualTo(pessoaAtualizada.getNome());
        assertThat(pessoa.getDataDeNascimento()).isEqualTo(pessoaAtualizada.getDataDeNascimento());
    }

    @Test
    @DisplayName("Deve ocorrer erro ao tentar atualizar uma pessoa inexistente.")
    public void updateInvalidPerson(){
        var pessoa = createARequestNull();
        String id = null;

        assertThrows(NullPointerException.class, () -> cadastroServices.update(pessoa, id));

        verify( cadastroRepository, never() ).save(requestPessoa(pessoa));
    }

    @Test
    @DisplayName("Deve criar pessoas 2")
    void savedPerson() {
        var pessoa = createARequest();

        //this.integration.consultarCep(pessoa.getEndereco().getCep());
        var savedPessoa = cadastroServices.save(pessoa);
        assertThat(savedPessoa.getId()).isNotNull();
        assertThat(savedPessoa.getNome()).isEqualTo("Camila");
        assertThat(savedPessoa.getDataDeNascimento()).isEqualTo("02/07/1996");
    }

    @Test //Está passando
    void whenFindArtistByIdReturnOptionalOfPessoaEntity() {
        Optional<Pessoa> entityResponse = PessoaServiceStubResponse();
        Optional<Pessoa> entityExpected = PessoaServiceStubExpected();

        when(cadastroRepository.findById("1"))
                .thenReturn(entityResponse);

        var actual = cadastroServices.findById("1");
        assertEquals(entityExpected, toEntityOptional(actual));
    }

    @Test
    @DisplayName("Deve lançar uma excessão ao tentar deletar uma pessoa inexistente")
    void shouldNotDeleteAValidPerson() {
        var pessoa = new Pessoa();
        String id = null;

        assertThrows(ApiNotFoundException.class, () -> cadastroServices.delete(id));

        verify(cadastroRepository, never()).delete(pessoa);
    }

    @Test
     void deletePeolpleByIDs() {
        List<String> ids = Arrays.asList("1", "2");
        cadastroServices.deletePeolpleByIDs(ids);
        verify(cadastroRepository).deleteAllById(ids);
    }

    @Test
    void deleteAll() {
        cadastroServices.deleteAll();
        verify(cadastroRepository).deleteAll();
    }

    private static Pessoa createAEntity() {
        return Pessoa.builder()
                .id("1")
                .nome("Camila")
                .dataDeNascimento("02/07/1996")
                .endereco(createAEntityCep())
                .build();
    }


    private static CepEntity createAEntityCep() {
        return CepEntity.builder()
                .cep("94020070")
                .logradouro("Rua Joao Dutra")
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
                .endereco(createAEntityCep())
                .build();
    }

    private static PessoaResponse createAResponse() {
        return PessoaResponse.builder()
                .id("1")
                .nome("Camila")
                .dataDeNascimento("02/07/1996")
                .endereco(createAEntityCep())
                .build();
    }

    private static PessoaRequest createARequestNull() {
        return PessoaRequest.builder()
                .id(null)
                .nome(null)
                .dataDeNascimento(null)
                .endereco(createAEntityCep())
                .build();
    }
}