package com.example.cepapi.integration.resttemplate.walmart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataIntegrationResponse {
    public ResponseData data;
}
