package com.example.cepapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataDeNascimento;
}
