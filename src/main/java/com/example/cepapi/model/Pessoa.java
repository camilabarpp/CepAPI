package com.example.cepapi.model;

import com.example.cepapi.model.dto.EnderecoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;



@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "CepAPI")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonFormat(pattern = "xxxx")
    private String id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataDeNascimento;
    private String numero;


    private List<EnderecoDTO> enderecoDTOS = new ArrayList<>();

    public List<EnderecoDTO> getEnderecoDTOS() {
        return enderecoDTOS;
    }

    public void setEnderecoDTOS(List<EnderecoDTO> enderecoDTOS) {
        this.enderecoDTOS = enderecoDTOS;
    }
}
