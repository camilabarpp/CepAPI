package com.example.cepapi.controller;

import com.example.cepapi.integration.resttemplate.cep.IntegrationCep;
import com.example.cepapi.integration.resttemplate.weather.IntegrationWeather;
import com.example.cepapi.model.cep.response.CepResponse;
import com.example.cepapi.model.pessoa.request.PessoaRequest;
import com.example.cepapi.model.pessoa.response.PessoaResponse;
import com.example.cepapi.model.weather.response.WeatherResponse;
import com.example.cepapi.service.CadastroServices;
import com.example.cepapi.service.WeatherService;
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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
	WeatherService service;

	private IntegrationWeather integrationWeather;

	private IntegrationCep integrationCep;

	@GetMapping(value = "")
	@ApiOperation("Show a list of peolple")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Returns a list of people"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error")})
	public List<PessoaResponse> findAll(@RequestParam(required = false) String nome){

        if (nome != null) {
           return cadastroServices.findByNome(nome);

        } else {
            return this.cadastroServices.findAll();

        }


		//return this.cadastroServices.findAll();
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
	@ApiOperation("Change a person by id")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Change a person by id"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error")})
	public PessoaResponse update(@PathVariable @Valid String id, @RequestBody PessoaRequest pessoaRequest){
		return pessoaResponse(cadastroServices.update(id, requestPessoa(pessoaRequest)));
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
			return Arrays.stream(cookies)
					.map(c -> c.getName() + ": " +
							c.getValue()).collect(Collectors.joining("\n"));
		}
		log.info("Mostrando todos os cookies!");
		return "Nenhum cookie encontrado!";
	}

	@GetMapping("/nome")
	public List<PessoaResponse> findByNome(@RequestParam String nome) {
        if (nome != null) {
            nome = URLEncoder.encode(nome, StandardCharsets.UTF_8);
            var list = cadastroServices.findByNome(nome);

            if (list == null) {
                throw new NullPointerException();
            }
            return cadastroServices.findByNome(nome);

        } else {
            return this.cadastroServices.findAll();

        }
	}

	@GetMapping("/city/")
	public WeatherResponse getWeather(@RequestParam("city") String city) {
		return integrationWeather.getWeather(city);
	}

	@GetMapping("/cep/{cep}")
	public CepResponse getCep(@PathVariable String cep) {
		return integrationCep.consultarCep(cep);
	}

	/*@GetMapping("/{city}")
	@ApiOperation("cvv")
	public WeatherResponse getWeather(@PathVariable String city) {
		return weatherClient.getWeather(city);
	}*/

}