package com.happy.shoppingcart.api.controller.domain;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CalculateReponse {

    @JsonProperty("http_status")
    private int httpStatus;

    @JsonProperty("status_code")
    private String statusCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("point")
    private int point;

    @JsonProperty("total")
    private BigDecimal total;

    @JsonProperty("cart_id")
    private int cartId;

    @JsonProperty("payload")
    private List<CalculateResponsePayload> payload;
    
}