package com.example.cepapi.repository;

import com.example.cepapi.model.pessoa.Pessoa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.cepapi.repository.PessoaRepositoryStub.createAEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataMongoTest
public class CepRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private CadastroRepository cadastroRepository;

    @Test
    @DisplayName("Deve retornar verdadeiro quando encontrar id no banco de dados")
    public void returnsTrueWhenIdExists() {
        Pessoa pessoa = createAEntity();
        mongoTemplate.save(pessoa);

        boolean exists = cadastroRepository.existsById(pessoa.getId());

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Deve retornar falso quando não encontrar id no banco de dados")
    public void returnsFalseWhenIdExists() {
        String id = "10";

        boolean exists = cadastroRepository.existsById(id);

        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Deve salvar uma pessoa no banco de dados")
    public void shouldSaveAPerson() {
        MongoTemplate mongoTemplate = mock(MongoTemplate.class);

        Pessoa pessoa = createAEntity();
        mongoTemplate.save(pessoa);

        Pessoa pessoaSalva = cadastroRepository.save(pessoa);

        assertThat(pessoaSalva.getId()).isNotNull();

        verify(mongoTemplate).save(pessoa);

    }

    @Test
    @DisplayName("Deve alterar uma pessoa no banco de dados")
    public void shouldUpdateAPerson() {
        MongoTemplate mongoTemplate = mock(MongoTemplate.class);

        Pessoa pessoa = createAEntity();
        mongoTemplate.save(pessoa);

        Pessoa atualizar = new Pessoa();

        atualizar.setId(pessoa.getId());
        atualizar.setNome(pessoa.getNome());
        atualizar.setDataDeNascimento(pessoa.getDataDeNascimento());
        atualizar.setEndereco(pessoa.getEndereco());

        Pessoa atualizada = cadastroRepository.save(pessoa);

        assertThat(atualizada.getId()).isNotNull();

        verify(mongoTemplate).save(pessoa);

    }

    @Test
    @DisplayName("Deve deletar uma pessoa no banco de dados")
    public void shouldDeleteAPerson() {
        Pessoa pessoa = createAEntity();
        mongoTemplate.save(pessoa, "test");

        Pessoa pessoaEncontrada = mongoTemplate.findById(pessoa.getId(), Pessoa.class);

        assert pessoaEncontrada != null;
        cadastroRepository.delete(pessoaEncontrada);

        Pessoa pessoaDeletada = mongoTemplate.findById(pessoa.getId(), Pessoa.class);

        assertThat(pessoaDeletada).isNull();
    }
}
