package com.example.cepapi.controller;

import com.example.cepapi.model.dto.EnderecoDTO;
import com.example.cepapi.model.Pessoa;
import com.example.cepapi.service.CadastroServices;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("")
public class CepRestController {
	private final CadastroServices cadastroServices;
	public CepRestController(CadastroServices cadastroServices) {
		this.cadastroServices = cadastroServices;
	}

	@ResponseBody
	@GetMapping("/{cep}")
	public ResponseEntity<EnderecoDTO> consultaCep(@PathVariable String cep) {
		return new ResponseEntity<>(cadastroServices.consultaCep(cep), HttpStatus.OK);
	}

	@GetMapping("")
	public List<Pessoa> findAll(){
		return this.cadastroServices.findAll();
	}

	@GetMapping("buscarid/{id}")
	public ResponseEntity<Pessoa> findById(@PathVariable String id) {
		return ResponseEntity.ok().body(cadastroServices.findById(id));
	}

	@ResponseBody
	@PostMapping("cadastrar")
	public ResponseEntity<Pessoa> create(@RequestBody Pessoa newPessoa, EnderecoDTO enderecoDTO, String cep) throws Exception {

/*		URL url = new URL("https://viacep.com.br/ws/" + newPessoa.consultaCep() + "/json");

		URLConnection connection = url.openConnection();
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

		StringBuilder jsonCep = new StringBuilder();

		while((cep = br.readLine()) != null) {
			jsonCep.append(cep);
		}

		Pessoa pessoaAux = new Gson().fromJson(jsonCep.toString(), Pessoa.class);
		newPessoa.setCep(pessoaAux.consultaCep());
		newPessoa.setLogradouro(pessoaAux.getLogradouro());
		newPessoa.setBairro(pessoaAux.getBairro());
		newPessoa.setLocalidade(pessoaAux.getLocalidade());
		newPessoa.setUf(pessoaAux.getUf());
		newPessoa = cadastroServices.create(newPessoa);
		return new ResponseEntity<>(newPessoa, HttpStatus.OK);*/
		for (int i = 0; i < newPessoa.getEnderecoDTOS().size(); i++) {

			enderecoDTO = cadastroServices.consultaCep(newPessoa.getEnderecoDTOS().get(i).getCep());

			newPessoa.getEnderecoDTOS().get(i).setLogradouro(enderecoDTO.getLogradouro());
			newPessoa.getEnderecoDTOS().get(i).setBairro(enderecoDTO.getBairro());
			newPessoa.getEnderecoDTOS().get(i).setLocalidade(enderecoDTO.getLocalidade());
			newPessoa.getEnderecoDTOS().get(i).setUf(enderecoDTO.getUf());
		}

		return new ResponseEntity<Pessoa>(newPessoa, HttpStatus.OK);
	}

	/*@PutMapping("atualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	Pessoa update(@RequestBody Pessoa newPessoa, @PathVariable String id) throws Exception {
		URL url2 = new URL("https://viacep.com.br/ws/" + newPessoa.getCep() + "/json");
		URLConnection connection = url2.openConnection();
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
		String cep = "";
		StringBuilder jsonCep2 = new StringBuilder();

		while((cep = br.readLine()) != null) {
			jsonCep2.append(cep);
		}

		Pessoa pessoaAux = new Gson().fromJson(jsonCep2.toString(), Pessoa.class);
		newPessoa.setCep(pessoaAux.getCep());
		newPessoa.setLogradouro(pessoaAux.getLogradouro());
		newPessoa.setBairro(pessoaAux.getBairro());
		newPessoa.setLocalidade(pessoaAux.getLocalidade());
		newPessoa.setUf(pessoaAux.getUf());
		return this.cadastroServices.update(newPessoa, id);
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		this.cadastroServices.delete(id);
		return ResponseEntity.noContent().build();
	}*/


}