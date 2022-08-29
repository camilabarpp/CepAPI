package com.example.cepapi.controller;

import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.service.CadastroServices;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Slf4j
@RestController
@RequestMapping("v1/api/")
@AllArgsConstructor
public class CadastroController {
	private final CadastroServices cadastroServices;

	@GetMapping("")
	@ApiOperation("Show a list of peolple")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Returns a list of people"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error")})
	public List<PessoaResponse> findAll(){
		return this.cadastroServices.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation("Show a person by id")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Returns an person by id"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error")})
	public PessoaResponse findById(@PathVariable String id) {
		return cadastroServices.findById(id);
	}

	@PostMapping()
	@ResponseBody
	@ResponseStatus(CREATED)
	@ApiOperation("Create a peson")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Create a person"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error")})
	public PessoaResponse create(@RequestBody @Valid PessoaRequest pessoaRequest) {
		return pessoaResponse(cadastroServices.create(requestPessoa(pessoaRequest)));
	}

	@PutMapping("/{id}")
	@ResponseStatus(CREATED)
	@ApiOperation("Chsnge a person by id")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Change a person by id"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error")})
	public PessoaResponse update(@PathVariable @Valid String id, @RequestBody PessoaRequest pessoaRequest){
		return this.cadastroServices.update(id, requestPessoa(pessoaRequest));
	}

	@DeleteMapping()
	@ResponseStatus(NO_CONTENT)
	@ApiOperation("Delete a list of people or all peolple")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Delete a list of people or all peolple or by id"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error")})
	public void deletePeolpleByIDs(@RequestParam(required = false) List<String> id){
		cadastroServices.deletePeolpleByIDs(id);
	}

	@GetMapping("/create-coockie")
	@ApiOperation("Create a cookie")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Create a cookie"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error")})
	public String criandoCoookie(Pessoa pessoa, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("cookieTest", "cookie-value");
		cookie.getValue();
		cookie.getName();
		cookie.setMaxAge(60 * 60 * 24);
		response.addCookie(cookie);
		return "cookie-recived/";
	}
}