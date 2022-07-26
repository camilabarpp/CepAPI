package com.example.cepapi.configuration.errorresponse;

import com.example.cepapi.configuration.errorobject.ErrorObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ErrorResponse {
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
private LocalDateTime timestamp;
    private List<ErrorObject> error;
}


