package com.happy.shoppingcart.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @GetMapping("/add_cart")
    public ResponseEntity<String> addCart(@RequestParam(name = "product_id") String productId,
                                            @RequestParam(name = "cart_id") String cartId){
        
        return ResponseEntity.ok().body("");
    }

}
