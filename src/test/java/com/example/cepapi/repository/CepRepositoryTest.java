package com.example.cepapi.repository;

import com.example.cepapi.cep.repository.CepRepository;
import com.example.cepapi.cep.model.CepEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.cepapi.controller.stub.PessoaControllerStub.createAEntityCep;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataMongoTest //se concentra somente nos componentes do mongo
@EnableMongoRepositories //para ativar os repositories do mongo
class CepRepositoryTest {
    @SpyBean //é usada para aplicar espiões do Mockito ao ApplicationContext sem substituir o bean existente
    private MongoTemplate mongoTemplate;
    @Autowired
    private CepRepository cepRepository;

    @Test
    @DisplayName("Deve retornar verdadeiro quando encontrar id no banco de dados")
    void returnsTrueWhenIdExists() {
        var cep = createAEntityCep();
        mongoTemplate.save(cep);

        var exists = cepRepository.existsById(cep.getCep());

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Deve retornar falso quando não encontrar id no banco de dados")
    void returnsFalseWhenIdExists() {
        String id = "10";

        var exists = cepRepository.existsById(id);

        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Deve salvar uma pessoa no banco de dados")
    void shouldSaveAPerson() {

        var cep = createAEntityCep();
        mongoTemplate.save(cep);

        var cepSaved = cepRepository.save(cep);

        assertThat(cepSaved.getCep()).isNotNull();

        verify(mongoTemplate).save(cep);

    }

    @Test
    @DisplayName("Deve alterar uma pessoa no banco de dados")
    void shouldUpdateAPerson() {

        var cep = createAEntityCep();
        mongoTemplate.save(cep);

        var cepUpdate = new CepEntity();

        cepUpdate.setCep(cep.getCep());
        cepUpdate.setLogradouro(cep.getLogradouro());
        cepUpdate.setBairro(cep.getBairro());
        cepUpdate.setLocalidade(cep.getLocalidade());
        cepUpdate.setUf(cep.getUf());

        var atualizada = cepRepository.save(cep);

        assertThat(atualizada.getCep()).isNotNull();
        assertEquals(atualizada.getCep(), cepUpdate.getCep());
        assertEquals(atualizada.getLogradouro(), cepUpdate.getLogradouro());
        assertEquals(atualizada.getBairro(), cepUpdate.getBairro());
        assertEquals(atualizada.getLocalidade(), cepUpdate.getLocalidade());
        assertEquals(atualizada.getUf(), cepUpdate.getUf());

        verify(mongoTemplate).save(cep);

    }
    @Test
    @DisplayName("Deve deletar uma pessoa no banco de dados")
    void shouldDeleteAPerson() {
        var cep = createAEntityCep();
        mongoTemplate.save(cep);

        var cepFound = mongoTemplate.findById(cep.getCep(), CepEntity.class);

        assert cepFound != null;
        cepRepository.delete(cepFound);

        var weatherDeleted = mongoTemplate.findById(cep.getCep(), CepEntity.class);

        assertThat(weatherDeleted).isNull();
    }
}
