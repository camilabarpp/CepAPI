package com.example.cepapi.model.pessoa.response;

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


}
