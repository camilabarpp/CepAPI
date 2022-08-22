package com.example.cepapi.model.cep;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("CEP")
public class CepEntity {
    @Id
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
