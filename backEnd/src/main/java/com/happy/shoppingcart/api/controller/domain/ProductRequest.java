package com.happy.shoppingcart.api.controller.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductRequest {
    @JsonProperty("shipping_id")
    private int shippingId;

    @JsonProperty("cart_id")
    private int cartId;
}
