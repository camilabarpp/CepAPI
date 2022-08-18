package com.example.cepapi.model.response;

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

    @NotNull
    @NotBlank
    @JsonProperty("id")
    private String id;
    @JsonProperty("nome")
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("dataDeNascimento")
    private String dataDeNascimento;
    @NotNull
    @NotBlank
    @JsonProperty("cep")
    private String cep;
    @JsonProperty("logradouro")
    private String logradouro;
    @JsonProperty("numero")
    private String numero;
    @JsonProperty("bairro")
    private String bairro;
    @JsonProperty("localidade")
    private String localidade;
    @JsonProperty("uf")
    private String uf;

}
