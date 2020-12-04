package com.happy.shoppingcart.api.controller.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductResponse {
    
    @JsonProperty("status_code")
    private int statusCode;
    private String message;
    private Integer point;
    @JsonProperty("total_with_ship")
    private BigDecimal totalWithShip;
    @JsonProperty("shipping_name")
    private String shippingName;
    private List<ProductPayload> payload;
    @JsonProperty("cart_id")
    private Integer cartId;
}
