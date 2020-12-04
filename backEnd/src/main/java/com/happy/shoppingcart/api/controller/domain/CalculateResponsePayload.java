package com.happy.shoppingcart.api.controller.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class CalculateResponsePayload {

    @JsonProperty("product_id")
    private Integer productId;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal price;
    
    @JsonProperty("img")
    private String img;
}
