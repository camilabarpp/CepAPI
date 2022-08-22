package com.example.cepapi.model.pessoa;

import com.example.cepapi.model.pessoa.cep.CepEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


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
    private CepEntity cepEntity;

    public CepEntity getEndereco() {
        return cepEntity;
    }

    public void setEndereco(CepEntity cepEntity) {
        this.cepEntity = cepEntity;
    }
}
