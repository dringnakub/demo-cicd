package com.happy.shoppingcart.api.controller;

import com.happy.shoppingcart.api.controller.domain.ProductResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @GetMapping("add_cart")
    public ResponseEntity<String> addCart(@RequestParam(name = "product_id") int productId,
                                            @RequestParam(name = "cart_id") int cartId){
        
        return ResponseEntity.ok().body("");
    }

    @PostMapping("calculate")
    public ProductResponse calculateAll() {
        ProductResponse response = new ProductResponse();
        response.setStatusCode(200);
        return response;
    }

}
