package com.happy.shoppingcart.api.controller;

import com.happy.shoppingcart.api.controller.domain.CalculateReponse;
import com.happy.shoppingcart.api.controller.domain.ProductResponse;
import com.happy.shoppingcart.api.service.CalculateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired private CalculateService calculateService;

    @GetMapping(value = "add_cart", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalculateReponse> addCart(@RequestParam(name = "product_id") Integer productId,
                                            @RequestParam(name = "cart_id", required = false) Integer cartId){
        CalculateReponse reponse = calculateService.calculatePrice(productId);
        return ResponseEntity.ok().body(reponse);
    }

    @PostMapping("calculate")
    public ProductResponse calculateAll() {
        ProductResponse response = new ProductResponse();
        response.setStatusCode(200);
        return response;
    }

}
