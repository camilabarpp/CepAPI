package com.example.cepapi.model.pessoa.request;

import com.example.cepapi.model.pessoa.cep.CepEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class PessoaRequest {

    @NotNull
    @NotBlank
    private String id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataDeNascimento;
    private CepEntity cepEntity;

    public CepEntity getEndereco() {
        return cepEntity;
    }

    public void setEndereco(CepEntity cepEntity) {
        this.cepEntity = cepEntity;
    }}
