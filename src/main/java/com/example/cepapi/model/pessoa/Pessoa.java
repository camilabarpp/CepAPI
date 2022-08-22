package com.example.cepapi.model.pessoa;

import com.example.cepapi.model.cep.CepEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Document(collection = "CepAPI")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    @Id
    private String id;
    @NotNull
    @NotBlank
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataDeNascimento;
    private CepEntity endereco;
    public CepEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(CepEntity endereco) {
        this.endereco = endereco;
    }

}
