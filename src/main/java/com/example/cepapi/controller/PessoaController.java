package com.example.cepapi.controller;

import com.example.cepapi.integration.resttemplate.CepClient;
import com.example.cepapi.model.request.PessoaRequest;
import com.example.cepapi.model.response.PessoaResponse;
import com.example.cepapi.service.CadastroServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;


@Slf4j
@RestController
@RequestMapping("")
@AllArgsConstructor
public class PessoaController {
	private final CadastroServices cadastroServices;
	private CepClient cepClient;

	@ResponseBody
	@GetMapping("/{cep}")
	public PessoaResponse consultaCep(@PathVariable String cep) {
		return cepClient.consultaCep(cep);
	}

	@GetMapping("")
	@ResponseStatus(OK)
	@ApiOperation(value = "Returns a list of people")
	public List<PessoaResponse> findAll(){
		return this.cadastroServices.findAll();
	}

	@GetMapping("buscarid/{id}")
	@ApiOperation(value = "Returns an person by id")
	public PessoaResponse findById(@PathVariable String id) {
		return cadastroServices.findById(id);
	}


	@PostMapping("/cadastrar")
	@ResponseBody
	@ResponseStatus(CREATED)
	public PessoaResponse create2(@RequestBody @NotNull PessoaRequest pessoaRequest) {
		var cepPesquisado = cepClient.consultaCep(pessoaRequest.getCep());
		integration(pessoaRequest, cepPesquisado);
		return cadastroServices.create(pessoaRequest);
	}


/*	@PutMapping("atualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Change an employee by id")
	public PessoaResponse update(
			@RequestBody PessoaRequest pessoaRequest ,
			@PathVariable String id){
		return this.cadastroServices.update(pessoaRequest, id);
	}*/

	@DeleteMapping("/deletar/{id}")
	@ResponseStatus(NO_CONTENT)
	@ApiOperation(value = "Delete a person by ID")
	public void delete(@PathVariable String id){
		this.cadastroServices.delete(id);
	}


	@DeleteMapping("/delete")
	@ResponseStatus(NO_CONTENT)
	@ApiOperation(value = "Delete a list of employees")
	public void deletePeolpleByIDs(@RequestParam List<String> ids){
		cadastroServices.deletePeolpleByIDs(ids);
	}

	private static void integration(PessoaRequest pessoaRequest, PessoaResponse cepPesquisado) {
		pessoaRequest.setCep(cepPesquisado.getCep());
		pessoaRequest.setLogradouro(cepPesquisado.getLogradouro());
		pessoaRequest.setBairro(cepPesquisado.getBairro());
		pessoaRequest.setLocalidade(cepPesquisado.getLocalidade());
		pessoaRequest.setUf(cepPesquisado.getUf());
	}

}