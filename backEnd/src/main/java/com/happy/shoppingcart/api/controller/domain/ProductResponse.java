package com.happy.shoppingcart.api.controller.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    @JsonProperty("status_code")
    private Integer statusCode;
    private String message;
    private Integer point;
    @JsonProperty("total_with_ship")
    private String totalWithShip;
    @JsonProperty("shipping_name")
    private String shippingName;
    private List<ProductPayload> payload;
    @JsonProperty("cart_id")
    private String cartId;
}
