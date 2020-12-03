package com.happy.shoppingcart.api.controller.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    
    @JsonProperty("status_code")
    private int statusCode;
    private String message;
    private List<ProductResponsePayload> payload;
}
