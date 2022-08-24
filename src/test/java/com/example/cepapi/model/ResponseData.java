package com.example.cepapi.model;

import com.example.cepapi.model.cep.response.CepResponse;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseData {
   @NotNull
   private ResponseProduct product;
}

