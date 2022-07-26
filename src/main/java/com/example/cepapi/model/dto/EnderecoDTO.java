package com.example.cepapi.model.dto;

import com.example.cepapi.model.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
