package com.example.cepapi.controller;

import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.mapper.PessoaMapper;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.service.CadastroServices;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.pessoaResponse;
import static com.example.cepapi.model.pessoa.mapper.PessoaMapper.requestPessoa;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequestMapping("v1/api/")
@AllArgsConstructor
public class CadastroController {
	private final CadastroServices cadastroServices;

	@GetMapping("")
	@ResponseStatus(OK)
	@ApiOperation(value = "Returns a list of people")
	public List<PessoaResponse> findAll(){
		return this.cadastroServices.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Returns an person by id")
	public PessoaResponse findById(@PathVariable String id) {
		return cadastroServices.findById(id);
	}

	@PostMapping()
	@ResponseBody
	@ResponseStatus(CREATED)
	//@ApiOperation(value = "Create a person")
	public PessoaResponse create(@RequestBody @Valid PessoaRequest pessoaRequest) {
		return pessoaResponse(cadastroServices.create(requestPessoa(pessoaRequest)));
	}

	@PutMapping("/{id}")
	@ResponseStatus(CREATED)
	@ApiOperation(value = "Change an employee by id")
	public PessoaResponse update(@PathVariable @Valid String id, @RequestBody PessoaRequest pessoaRequest){
		return this.cadastroServices.update(id, requestPessoa(pessoaRequest));
	}

	@DeleteMapping()
	@ResponseStatus(NO_CONTENT)
	@ApiOperation(value = "Delete a list of people or all peolple")
	public void deletePeolpleByIDs(@RequestParam(required = false) List<String> id){
		cadastroServices.deletePeolpleByIDs(id);
	}

	@GetMapping("/create-coockie")
	public String criandoCoookie(Pessoa pessoa, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("cookieTest", "cookie-value");
		cookie.getValue();
		cookie.getName();
		cookie.setMaxAge(60 * 60 * 24);
		response.addCookie(cookie);
		return "cookie-recived/";
	}

	@GetMapping("/filtro")
	public List<PessoaResponse> findPessoaNomeContains(@RequestParam String nome) {
		return cadastroServices.findByNomeContains(nome)
				.stream()
				.map(PessoaMapper::pessoaResponse)
				.toList();
	}


}