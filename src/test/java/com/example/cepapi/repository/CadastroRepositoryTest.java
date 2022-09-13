package com.example.cepapi.repository;

import com.example.cepapi.model.pessoa.Pessoa;
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

import static com.example.cepapi.repository.CadastroRepositoryStub.createAEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataMongoTest //se concentra somente nos componentes do mongo
@EnableMongoRepositories //para ativar os repositories do mongo
class CadastroRepositoryTest {
    @SpyBean //é usada para aplicar espiões do Mockito ao ApplicationContext sem substituir o bean existente
    private MongoTemplate mongoTemplate;
    @Autowired
    private CadastroRepository cadastroRepository;

    @Test
    @DisplayName("Deve retornar verdadeiro quando encontrar id no banco de dados")
    void returnsTrueWhenIdExists() {
        Pessoa pessoa = createAEntity();
        mongoTemplate.save(pessoa);

        var exists = cadastroRepository.existsById(pessoa.getId());

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Deve retornar falso quando não encontrar id no banco de dados")
    void returnsFalseWhenIdExists() {
        String id = "10";

        var exists = cadastroRepository.existsById(id);

        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Deve salvar uma pessoa no banco de dados")
    void shouldSaveAPerson() {

        var pessoa = createAEntity();
        mongoTemplate.save(pessoa);

        var pessoaSalva = cadastroRepository.save(pessoa);

        assertThat(pessoaSalva.getId()).isNotNull();

        verify(mongoTemplate).save(pessoa);

    }

    @Test
    @DisplayName("Deve alterar uma pessoa no banco de dados")
    void shouldUpdateAPerson() {

        var pessoa = createAEntity();
        mongoTemplate.save(pessoa);

        var atualizar = new Pessoa();

        atualizar.setId(pessoa.getId());
        atualizar.setNome(pessoa.getNome());
        atualizar.setDataDeNascimento(pessoa.getDataDeNascimento());
        atualizar.setEndereco(pessoa.getEndereco());

        var atualizada = cadastroRepository.save(pessoa);

        assertThat(atualizada.getId()).isNotNull();

        verify(mongoTemplate).save(pessoa);

    }

    @Test
    @DisplayName("Deve deletar uma pessoa no banco de dados")
    void shouldDeleteAPerson() {
        var pessoa = createAEntity();
        mongoTemplate.save(pessoa, "test");

        var pessoaEncontrada = mongoTemplate.findById(pessoa.getId(), Pessoa.class);

        assert pessoaEncontrada != null;
        cadastroRepository.delete(pessoaEncontrada);

        var pessoaDeletada = mongoTemplate.findById(pessoa.getId(), Pessoa.class);

        assertThat(pessoaDeletada).isNull();
    }
}
