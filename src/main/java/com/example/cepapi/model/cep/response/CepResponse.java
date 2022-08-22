package com.example.cepapi.model.cep.response;

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
@NoArgsConstructor
@AllArgsConstructor
public class CepResponse {

    @Id
    @JsonProperty("cep")
    private String cep;
    @JsonProperty("logradouro")
    private String logradouro;
    @JsonProperty("bairro")
    private String bairro;
    @JsonProperty("localidade")
    private String localidade;
    @JsonProperty("uf")
    private String uf;
}
