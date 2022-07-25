package com.example.cepapi.controller;

import com.example.cepapi.model.Pessoa;
import com.example.cepapi.service.CadastroServices;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("")
public class CepRestController {

	@Autowired
	private CadastroServices cadastroServices;



	@ResponseBody
	@GetMapping("/{cep}")
	public ResponseEntity<Pessoa> getcep(@PathVariable String cep) {
		return new ResponseEntity<Pessoa>(cadastroServices.getCep(cep), HttpStatus.OK);
		//return new RestTemplate().getForEntity("https://viacep.com.br/ws/" + cep + "/json/", Endereco.class).getBody();
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
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa newPessoa) throws Exception {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newPessoa.getId()).toUri();

		URL url = new URL("https://viacep.com.br/ws/" + newPessoa.getCep() + "/json");
		URLConnection connection = url.openConnection();
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String cep = "";
		StringBuilder jsonCep = new StringBuilder();

		while((cep = br.readLine()) != null) {
			jsonCep.append(cep);
		}

		Pessoa pessoaAux = new Gson().fromJson(jsonCep.toString(), Pessoa.class);
		newPessoa.setCep(pessoaAux.getCep());
		newPessoa.setLogradouro(pessoaAux.getLogradouro());
		newPessoa.setBairro(pessoaAux.getBairro());
		newPessoa.setLocalidade(pessoaAux.getLocalidade());
		newPessoa.setUf(pessoaAux.getUf());
		newPessoa = cadastroServices.create(newPessoa);
		return new ResponseEntity<Pessoa>(newPessoa, HttpStatus.OK);
    }

	@PutMapping("atualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	Pessoa update(@RequestBody Pessoa newPessoa, @PathVariable String id) throws Exception {
		URL url2 = new URL("https://viacep.com.br/ws/" + newPessoa.getCep() + "/json");
		URLConnection connection = url2.openConnection();
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
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
		//return newPessoa = cadastroServices.update(newPessoa, id);
		return this.cadastroServices.update(newPessoa, id);
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		this.cadastroServices.delete(id);
		return ResponseEntity.noContent().build();
	}


}