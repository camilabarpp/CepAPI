package com.example.cepapi.controller;

import com.example.cepapi.model.pessoa.mapper.PessoaMapper;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.service.CadastroServices;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;


@Slf4j
@RestController
@RequestMapping("")
@AllArgsConstructor
public class PessoaController {
	private final CadastroServices cadastroServices;

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

	@PostMapping
	public ResponseEntity<PessoaResponse> inserir(@RequestBody PessoaRequest cliente) {
		cadastroServices.inserir(cliente);
		return ResponseEntity.ok(PessoaMapper.toRequest(cliente));
	}
/*	@PostMapping("/cadastrar")
	@ResponseBody
	@ResponseStatus(CREATED)
	public PessoaResponse create2(@RequestBody @NotNull PessoaRequest pessoaRequest) {

		return cadastroServices.create(pessoaRequest);
	}


	@PutMapping("atualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Change an employee by id")
	public PessoaResponse update(@RequestBody PessoaRequest pessoaRequest , @PathVariable String id){
		return this.cadastroServices.update(pessoaRequest, id);
	}*/

	@DeleteMapping("/deletar/{id}")
	@ResponseStatus(NO_CONTENT)
	@ApiOperation(value = "Delete a person by ID")
	public void delete(@PathVariable String id){
		this.cadastroServices.delete(id);
	}


	@DeleteMapping("/deleteIds")
	@ResponseStatus(NO_CONTENT)
	@ApiOperation(value = "Delete a list of employees")
	public void deletePeolpleByIDs(@RequestParam List<String> ids){
		cadastroServices.deletePeolpleByIDs(ids);
	}

	@DeleteMapping("delete")
	@ResponseStatus(NO_CONTENT)
	@ApiOperation(value = "Delete all cities")
	public void deleteAll() {
		cadastroServices.deleteAll();
	}
}