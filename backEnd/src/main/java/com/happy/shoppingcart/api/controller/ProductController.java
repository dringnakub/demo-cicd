package com.happy.shoppingcart.api.controller;

import com.happy.shoppingcart.api.controller.domain.ProductPayload;
import com.happy.shoppingcart.api.controller.domain.ProductResponse;
import com.happy.shoppingcart.api.service.ProductService;
import com.happy.shoppingcart.common.entities.ProductDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("add_cart")
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
        List<ProductPayload> responsePayloadList = new ArrayList<>();
        if (productDbList != null) {
            for (ProductDb productDb : productDbList) {
                ProductPayload productPayload = new ProductPayload();
                productPayload.setProductId(productDb.getProductId());
                productPayload.setProductName(productDb.getProductName());
                productPayload.setPrice(productDb.getPrice() == null ? 0.0 : productDb.getPrice().doubleValue());
                productPayload.setImage(productDb.getImg());
                responsePayloadList.add(productPayload);
            }
        }
        response.setPayload(responsePayloadList);
        return ResponseEntity.ok(response);
    }

    @PostMapping("calculate")
    public ProductResponse calculateAll() {
        ProductResponse response = new ProductResponse();
        response.setStatusCode(200);
        return response;
    }

}
