package com.example.cepapi.model.pessoa.response;

import com.example.cepapi.model.cep.CepEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaResponse {
    private String id;
    private String nome;
    private String dataDeNascimento;
    private CepEntity endereco;
}
