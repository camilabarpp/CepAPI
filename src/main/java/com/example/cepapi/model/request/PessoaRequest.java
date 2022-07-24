package com.example.cepapi.model.request;

import com.example.cepapi.model.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class PessoaRequest {
    @Id
    private String id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataDeNascimento;
}
