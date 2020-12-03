package com.happy.shoppingcart.api.controller.domain;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Setter;

@Setter
public class CalculateReponse {

    @JsonProperty("http_status")
    private int httpStatus;

    @JsonProperty("status_code")
    private String statusCode;

    private String message;

    private BigDecimal point;

    private BigDecimal total;

    @JsonProperty("cart_id")
    private int cartId;

    private List<ProductPayload> payload;
    
}