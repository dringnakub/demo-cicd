package com.happy.shoppingcart.api.controller;

import com.happy.shoppingcart.api.controller.domain.ProductResponse;
import com.happy.shoppingcart.api.controller.domain.ProductResponsePayload;
import com.happy.shoppingcart.api.service.ProductService;
import com.happy.shoppingcart.common.entities.ProductDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("/add_cart")
    public ResponseEntity<String> addCart(@RequestParam(name = "product_id") int productId,
                                            @RequestParam(name = "cart_id") int cartId){
        
        return ResponseEntity.ok().body("");
    }
    
    @GetMapping("/list")
    public ResponseEntity<ProductResponse> getProductList(
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "gender", required = false) String gender) {

        ProductResponse response = new ProductResponse();
        response.setStatusCode(200);
        response.setMessage("success");

        List<ProductDb> productDbList = productService.getProductList(age, gender);
        List<ProductResponsePayload> responsePayloadList = new ArrayList<>();
        for (ProductDb productDb : productDbList) {
            ProductResponsePayload responsePayload = new ProductResponsePayload();
            responsePayload.setId(productDb.getProductId());
            responsePayload.setProductName(productDb.getProductName());
            responsePayload.setPrice(productDb.getPrice() == null ? 0.0 : productDb.getPrice().doubleValue());
            responsePayload.setImage(productDb.getImg());
            responsePayloadList.add(responsePayload);
        }
        response.setPayload(responsePayloadList);
        return ResponseEntity.ok(response);
    }

}
