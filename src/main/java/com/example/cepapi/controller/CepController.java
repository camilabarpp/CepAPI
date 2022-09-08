package com.example.cepapi.controller;

import com.example.cepapi.model.cep.response.CepResponse;
import com.example.cepapi.service.CepService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("v1/api/cep")
@AllArgsConstructor
public class CepController {

    private CepService service;

    @GetMapping("/{cep}")
    @ApiOperation("Get a CEP")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Get a CEP"),
            @ApiResponse(code = 404, message = "Schema not found"),
            @ApiResponse(code = 400, message = "Missing or invalid request body"),
            @ApiResponse(code = 500, message = "Internal error")})
    public CepResponse getCep(@PathVariable String cep) {
        log.info("Mostrando dados sobre o CEP " + cep);
        return service.consultarCep(cep);
    }
}
