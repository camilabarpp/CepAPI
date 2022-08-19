package com.example.cepapi.controller;

import com.example.cepapi.model.request.PessoaRequest;
import com.example.cepapi.model.response.PessoaResponse;
import com.example.cepapi.service.CadastroServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.annotations.ApiOperation;

import static org.springframework.http.HttpStatus.*;


@Slf4j
@RestController
@RequestMapping("city/")
@AllArgsConstructor
public class PessoaController {
	CadastroServices services;

	@GetMapping("")
	@ApiOperation(value = "Returns a list of cities")
	public List<PessoaResponse> findAll(){
		return services.findAll();
	}

	@GetMapping("buscarid/{id}")
	@ApiOperation(value = "Returns a city by id")
	public PessoaResponse findById(@PathVariable String id) {
		return services.findById(id);
	}

/*	@PostMapping("/cadastrar")
	@ResponseBody
	@ResponseStatus(CREATED)
	@ApiOperation(value = "Create a city")
	public PessoaResponse create(@RequestBody @NotNull PessoaRequest pessoaRequest) {
		return facade.create(pessoaRequest);
	}*/

	@PutMapping("atualizar/{id}")
	@ResponseStatus(CREATED)
	@ApiOperation(value = "Change a city by id")
	public PessoaResponse update(@RequestBody PessoaRequest pessoaRequest , @PathVariable String id){
		return services.update(pessoaRequest, id);
	}

	@DeleteMapping("/deletar/{id}")
	@ResponseStatus(NO_CONTENT)
	@ApiOperation(value = "Delete a city by ID")
	public void delete(@PathVariable String id){
		services.delete(id);
	}


	@DeleteMapping("/deleteIds")
	@ResponseStatus(NO_CONTENT)
	@ApiOperation(value = "Delete a list of cities")
	public void deleteByIDs(@RequestParam List<String> ids){
		services.deleteByIDs(ids);
	}

	@DeleteMapping("/deleteAll")
	@ResponseStatus(NO_CONTENT)
	@ApiOperation(value = "Delete all cities")
	public void deleteByIDs(){
		services.deleteAll();
	}

}