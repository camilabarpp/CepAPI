package com.example.cepapi.service;

import com.example.cepapi.integration.resttemplate.cep.IntegrationCep;
import com.example.cepapi.model.cep.CepEntity;
import com.example.cepapi.model.cep.response.CepResponse;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.repository.CepRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.example.cepapi.model.cep.CepMapper.entityToResponse;
import static com.example.cepapi.model.cep.CepMapper.responseToEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@RequiredArgsConstructor
class CepServiceTest {
    @Mock
    private CepRepository repository;
    @MockBean
    private CepService service;
    @Mock
    private IntegrationCep integrationCep;

    @Test
    void pesquisarCepESalvarNoBanco() {
        String cep = "94020070";
        Pessoa pessoa = createAEntity();
        CepEntity entity = createAEntityCep();

        when(integrationCep.consultarCep(cep)).thenReturn(responseToEntity(entity));
        when(repository.save(entity)).thenReturn(entity);

        service.pesquisarCepESalvarNoBanco(pessoa);

        assertEquals(pessoa.getEndereco().getCep(), entity.getCep());
        assertEquals(pessoa.getEndereco().getLogradouro(), entity.getLogradouro());
        assertEquals(pessoa.getEndereco().getBairro(), entity.getBairro());
        assertEquals(pessoa.getEndereco().getLocalidade(), entity.getLocalidade());
        assertEquals(pessoa.getEndereco().getUf(), entity.getUf());
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