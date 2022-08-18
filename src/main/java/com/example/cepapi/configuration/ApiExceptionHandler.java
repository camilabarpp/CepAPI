package com.example.cepapi.configuration;

import com.example.cepapi.configuration.errorresponse.ErrorResponse;
import com.example.cepapi.configuration.errorobject.ErrorObject;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ApiExceptionHandler extends DefaultResponseErrorHandler {

    @ExceptionHandler(ApiNotFoundException.class)
    @ResponseStatus (NOT_FOUND)
    public ErrorResponse handleApiRequestExceptionNotFound(ApiNotFoundException e) {
        return  ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .error(List.of(ErrorObject.builder()
                        .message(e.getMessage())
                        .field(NOT_FOUND.name())
                        .parameter(e.getClass().getSimpleName())
                        .build()))
                .build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus (METHOD_NOT_ALLOWED)
    public ErrorResponse methodArgumentNotValidException(HttpRequestMethodNotSupportedException exception) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .error(List.of(ErrorObject.builder()
                        .message(exception.getMessage())
                        .field(METHOD_NOT_ALLOWED.name())
                        .parameter(exception.getClass().getSimpleName())
                        .build()))
                .build();
    }
    @ExceptionHandler(IOException.class)
    @ResponseStatus (INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .error(List.of(ErrorObject.builder()
                        .message(e.getMessage())
                        .field(INTERNAL_SERVER_ERROR.name())
                        .parameter(e.getClass().getSimpleName())
                        .build()))
                .build();
    }

    //Erro para valores nulos
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse nullPointerException(NullPointerException e) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .error(List.of(ErrorObject.builder()
                        .message("Dados nulos ou incorretos!")
                        .field(INTERNAL_SERVER_ERROR.name())
                        .parameter(e.getClass().getSimpleName())
                        .build()))
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse httpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .error(List.of(ErrorObject.builder()
                        .message("Ímpossível ler o corpo da requisição!")
                        .field(BAD_REQUEST.name())
                        .parameter(e.getClass().getSimpleName())
                        .build()))
                .build();
    }

}