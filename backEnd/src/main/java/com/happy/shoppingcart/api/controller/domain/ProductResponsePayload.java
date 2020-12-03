package com.happy.shoppingcart.api.controller.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductResponsePayload {
    
    private int id;
    @JsonProperty("product_name")
    private String productName;
    private double price;
    private String image;
}
