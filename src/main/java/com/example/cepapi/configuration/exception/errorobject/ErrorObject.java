package com.example.cepapi.configuration.exception.errorobject;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
public class ErrorObject {
    private String message;
    private String field;
    private String parameter;
}


