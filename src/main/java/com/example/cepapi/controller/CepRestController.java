package com.example.cepapi.controller;

import com.example.cepapi.model.Pessoa;
import com.example.cepapi.model.Endereco;
import com.example.cepapi.service.CadastroServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("")
public class CepRestController {

	@Autowired
	private CadastroServices cadastroServices;

	@ResponseBody
	@GetMapping("/{cep}")
	public ResponseEntity<Endereco> getCep(@PathVariable String cep) {
		return new ResponseEntity<Endereco>(cadastroServices.getCep(cep), HttpStatus.OK);
		//return new RestTemplate().getForEntity("https://viacep.com.br/ws/" + cep + "/json/", Endereco.class).getBody();
	}

	@GetMapping("")
	public List<Pessoa> findAll(){
		return this.cadastroServices.findAll();
	}

	//Method POST
    @PostMapping("cadastrar")
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa newPessoa) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newPessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(cadastroServices.create(newPessoa));
    }




}