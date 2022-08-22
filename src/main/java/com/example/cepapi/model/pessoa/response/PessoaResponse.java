package com.example.cepapi.model.pessoa.response;

import com.example.cepapi.model.pessoa.cep.CepEntity;
import com.example.cepapi.model.pessoa.cep.request.CepRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaResponse {
    @JsonProperty("id")
    private String id;
    @JsonProperty("nome")
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("dataDeNascimento")
    private String dataDeNascimento;
    @JsonProperty("endereco")
    private CepEntity endereco;
}
