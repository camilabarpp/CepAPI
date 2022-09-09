package com.example.cepapi.controller;

import com.example.cepapi.configuration.exception.ApiNotFoundException;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.service.CadastroServices;
import io.swagger.annotations.Api;
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
import static java.lang.String.valueOf;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
@RestController
@RequestMapping("v1/api/")
@AllArgsConstructor
@Api("CEP API")
@Slf4j
public class CadastroController {
	private final CadastroServices cadastroServices;
/*	@GetMapping(value = "/find")
	@ApiOperation("Show a list of peolple")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Returns a list of people"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error")})
	public List<PessoaResponse> findAll(){
		log.info("Buscando todos as pessoas");
		return cadastroServices.findAll();
	}*/

    @GetMapping()
    @ApiOperation("Show a list of peolple by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Show a list of peolple by name"),
            @ApiResponse(code = 404, message = "Schema not found"),
            @ApiResponse(code = 400, message = "Missing or invalid request body"),
            @ApiResponse(code = 500, message = "Internal error")})
    public List<PessoaResponse> findByNome(@RequestParam(required = false) String nome){
		if (nome != null) {
			log.info("Buscando pessoa pelo nome " + nome);
			return cadastroServices.findByNome(nome);
		} else {
			log.info("Mostrando todas as pessoas");
			return cadastroServices.findAll();
		}
    }

	@GetMapping("/{id}")
	@ApiOperation("Show a person by id")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Returns an person by id"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error")})
	public PessoaResponse findById(@PathVariable String id) {
		log.info("Buscando pessoa pelo ID " + id);
		return pessoaResponse(cadastroServices.findById(id));
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
		log.info("Criando uma pessoa");
		return pessoaResponse(cadastroServices.create(requestPessoa(pessoaRequest)));
	}
	@PutMapping("/{id}")
	@ResponseStatus(CREATED)
	@ApiOperation("Change a person by id")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Change a person by id"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error")})
	public PessoaResponse update(@PathVariable @Valid String id, @RequestBody PessoaRequest pessoaRequest){
		log.info("Atualizando uma pessoa pelo ID " + id);
		return pessoaResponse(cadastroServices.update(id, requestPessoa(pessoaRequest)));
	}
	@DeleteMapping()
	@ResponseStatus(NO_CONTENT)
	@ApiOperation("Delete a list of people or all peolple")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Delete a list of people or all peolple or by id"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error")})
	public void deletePeolpleByIDs(@RequestParam(required = false) List<String> id){
		log.info("Deletando pessoa pelo ID " + id);
		cadastroServices.deletePeolpleByIDs(id);
	}
	@PostMapping("/{pessoa}")
	@ApiOperation("Create a cookie")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Create a cookie"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error")})
	public String criandoCoookie(HttpServletResponse response, @PathVariable("pessoa") String userName) {
		Cookie cookie = new Cookie("Pessoa", userName);
		cookie.getValue();
		cookie.getName();
		cookie.setMaxAge(60 * 60 * 24);
		response.addCookie(cookie);
		log.info("Criado cookie " + userName);
		return "cookie-recived/";
	}
	@GetMapping("/cookies/get")
	@ApiOperation("Get a cookie")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Get a cookie"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error")})
	public String readAllCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			log.info("Obtendo todos os cookies");
			return stream(cookies)
					.map(c -> c.getName() + ": " +
							c.getValue()).collect(joining("\n"));
		}
		log.info("Nenhum cookie encontrado!");
		return valueOf(new ApiNotFoundException("Nenhum cookie encontrado!"));
	}
}