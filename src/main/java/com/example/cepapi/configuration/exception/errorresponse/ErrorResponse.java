package com.example.cepapi.configuration.exception.errorresponse;

import com.example.cepapi.configuration.exception.errorobject.ErrorObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Builder
@Getter
@AllArgsConstructor
public class ErrorResponse {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
    private List<ErrorObject> error;
}


