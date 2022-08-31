package com.example.cepapi.service;

import com.example.cepapi.integration.resttemplate.cep.IntegrationCep;
import com.example.cepapi.model.cep.CepEntity;
import com.example.cepapi.model.cep.CepMapper;
import com.example.cepapi.model.cep.response.CepResponse;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.repository.CadastroRepository;
import com.example.cepapi.repository.CepRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.cepapi.model.cep.CepMapper.entityToResponse;
import static com.example.cepapi.model.cep.CepMapper.toProductEntity;
import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.requestPessoa;
import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.toEntityOptional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@RequiredArgsConstructor
class CepServiceTest {

    @MockBean
    CadastroRepository cadastroRepository;
    @Mock
    private CepRepository repository;
    @Mock
    private CepService service;
    @InjectMocks
    private CadastroServices cadastroServices;
    @Mock
    private IntegrationCep integrationCep;

    @Test
    void pesquisarCepESalvarNoBanco() {
        String cep = "94020070";
        Pessoa pessoa = createAEntity();
        CepEntity entity = createAEntityCep();

        repository.save(entity);

        when(integrationCep.consultarCep(cep)).thenReturn(CepMapper.responseToEntity(entity));
        service.pesquisarCepESalvarNoBanco(pessoa);

        assertEquals(pessoa.getEndereco().getCep(), entity.getCep());
        assertEquals(pessoa.getEndereco().getLogradouro(), entity.getLogradouro());
        assertEquals(pessoa.getEndereco().getBairro(), entity.getBairro());
        assertEquals(pessoa.getEndereco().getLocalidade(), entity.getLocalidade());
        assertEquals(pessoa.getEndereco().getUf(), entity.getUf());

        verify(repository).save(entity);

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