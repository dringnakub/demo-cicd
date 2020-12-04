package com.happy.shoppingcart.api.controller;

import com.happy.shoppingcart.api.controller.domain.CalculateReponse;
import com.happy.shoppingcart.api.controller.domain.ProductRequest;
import com.happy.shoppingcart.api.controller.domain.ProductResponse;
import com.happy.shoppingcart.api.service.CalculateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import com.happy.shoppingcart.api.controller.domain.ProductPayload;
import com.happy.shoppingcart.api.service.ProductService;
import com.happy.shoppingcart.common.entities.ProductTb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CalculateService calculateService;

    @GetMapping(value = "add_cart", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalculateReponse> addCart(@RequestParam(name = "product_id") Integer productId,
                                                    @RequestParam(name = "cart_id", required = false) Integer cartId) {
        CalculateReponse reponse = calculateService.calculatePrice(productId);
        return ResponseEntity.ok().body(reponse);
    }

    @GetMapping("list")
    public ResponseEntity<ProductResponse> getProductList(
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "gender", required = false) String gender) {

        ProductResponse response = new ProductResponse();
        response.setStatusCode(0);
        response.setMessage("success");

        List<ProductTb> ProductTbList = productService.getProductList(age, gender);
        List<ProductPayload> responsePayloadList = new ArrayList<>();
        if (ProductTbList != null) {
            for (ProductTb ProductTb : ProductTbList) {
                ProductPayload productPayload = new ProductPayload();
                productPayload.setProductId(ProductTb.getProductId());
                productPayload.setProductName(ProductTb.getProductName());
                productPayload.setPrice(ProductTb.getPrice() == null ? 0.0 : ProductTb.getPrice().doubleValue());
                productPayload.setImage(ProductTb.getImg());
                responsePayloadList.add(productPayload);
            }
        }
        response.setPayload(responsePayloadList);
        return ResponseEntity.ok(response);
    }

    @PostMapping("calculate")
    public ResponseEntity<ProductResponse> calculateAll(@RequestBody ProductRequest body) {
        ProductResponse result = productService.calculateAll(body.getShippingId(), body.getCartId());
        result.setStatusCode(200);
        result.setMessage("success");
        return ResponseEntity.ok(result);
    }

}
