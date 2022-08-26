package com.example.cepapi.model.cep.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CepRequest {
    @Id
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
