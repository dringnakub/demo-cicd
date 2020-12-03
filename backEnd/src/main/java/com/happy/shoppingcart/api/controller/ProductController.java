package com.happy.shoppingcart.api.controller;

import com.happy.shoppingcart.api.controller.domain.ProductResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @PostMapping("/api/v1/product/calculate")
    public ProductResponse calculateAll() {
        ProductResponse response = new ProductResponse();
        response.setStatusCode(200);
        return response;
    }
}
