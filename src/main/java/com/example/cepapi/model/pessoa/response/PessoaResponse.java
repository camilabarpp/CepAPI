package com.example.cepapi.model.pessoa.response;

import com.example.cepapi.model.cep.CepEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaResponse {

    @JsonProperty("nome")
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("dataDeNascimento")
    private String dataDeNascimento;
    @JsonProperty("endereco")
    private CepEntity endereco;
}
