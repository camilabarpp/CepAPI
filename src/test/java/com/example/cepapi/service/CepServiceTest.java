package com.example.cepapi.service;

import com.example.cepapi.cep.service.CepService;
import com.example.cepapi.configuration.resttemplate.cep.IntegrationCep;
import com.example.cepapi.cep.model.CepEntity;
import com.example.cepapi.cep.model.response.CepResponse;
import com.example.cepapi.registrationPeople.model.pessoa.Pessoa;
import com.example.cepapi.cep.repository.CepRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.cepapi.cep.model.CepMapper.entityToResponse;
import static com.example.cepapi.cep.model.CepMapper.responseToEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@RequiredArgsConstructor
class CepServiceTest {
    @Mock
    private CepRepository repository;
    @InjectMocks
    private CepService service;
    @Mock
    private IntegrationCep integrationCep;

    @Test
    @DisplayName("Pesquisar CEP e salvar no banco de dados")
    void pesquisarCepESalvarNoBanco() {
        String cep = "94020070";
        Pessoa pessoa = createAEntity();
        CepResponse response = createAResponseCep();
        CepEntity entity = createAEntityCep();

        when(integrationCep.consultarCep(cep)).thenReturn(responseToEntity(entity));
        when(repository.save(entity)).thenReturn(entityToResponse(response));

        service.pesquisarCepESalvarNoBanco(pessoa);
        pessoa.setEndereco(entity);

        assertEquals(pessoa.getEndereco().getCep(), entity.getCep());
        assertEquals(pessoa.getEndereco().getLogradouro(), entity.getLogradouro());
        assertEquals(pessoa.getEndereco().getBairro(), entity.getBairro());
        assertEquals(pessoa.getEndereco().getLocalidade(), entity.getLocalidade());
        assertEquals(pessoa.getEndereco().getUf(), entity.getUf());
    }

    @Test
    @DisplayName("Deve pesquisar cep da camada SERVICE")
    void shouldFindCep() {
        CepResponse response = new CepResponse();
        when(integrationCep.consultarCep("94020070"))
                .thenReturn(response);
        var actual = service.consultarCep("94020070");
        assertNotNull(actual);
    }

    public static Pessoa createAEntity() {
        return Pessoa.builder()
                .id("1")
                .nome("Camila")
                .dataDeNascimento("02/07/1996")
                .endereco(createAEntityCep())
                .build();
    }

    static CepEntity createAEntityCep() {
        return CepEntity.builder()
                .cep("94020070")
                .logradouro("Rua Joao Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build();
    }

    static CepResponse createAResponseCep() {
        return CepResponse.builder()
                .cep("94020070")
                .logradouro("Rua Joao Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build();
    }
}