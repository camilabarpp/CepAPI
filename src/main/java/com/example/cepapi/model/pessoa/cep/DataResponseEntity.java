package com.example.cepapi.model.pessoa.cep;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataResponseEntity {
    private CepEntity product;
}
