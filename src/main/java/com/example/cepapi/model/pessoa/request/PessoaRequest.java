package com.example.cepapi.model.pessoa.request;

import com.example.cepapi.model.cep.CepEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@AllArgsConstructor
@NoArgsConstructor
public class PessoaRequest {
    private String id;
    @NotBlank @NotNull
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

