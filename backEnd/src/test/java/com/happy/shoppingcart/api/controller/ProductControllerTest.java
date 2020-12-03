package com.happy.shoppingcart.api.controller;

import com.happy.shoppingcart.api.controller.domain.ProductResponse;
import com.happy.shoppingcart.api.controller.domain.ProductResponsePayload;
import com.happy.shoppingcart.api.service.ProductService;
import com.happy.shoppingcart.common.entities.ProductDb;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController unitUnderTest;
    @Mock
    private ProductService productService;
    
    @Test
    void whenGetProductListFoundData_thenProductListIsReturnedInPayload() {
        
        List<ProductDb> productList = new ArrayList<>();
        ProductDb toy = new ProductDb();
        toy.setProductId(15);
        toy.setProductName("OMG-Gossip Girl");
        toy.setPrice(BigDecimal.valueOf(24.95));
        toy.setImg("/img/15-omg-gossip-girl.jpg");
        productList.add(toy);
        given(productService.getProductList(any(), any())).willReturn(productList);
        
        ResponseEntity<ProductResponse> response = unitUnderTest.getProductList(15, "M");

        ProductResponse body = response.getBody();
        assertEquals(200, body.getStatusCode());
        assertEquals("success", body.getMessage());
        assertEquals(15, body.getPayload().get(0).getId());
        assertEquals("OMG-Gossip Girl", body.getPayload().get(0).getProductName());
        assertEquals(24.95, body.getPayload().get(0).getPrice());
        assertEquals("/img/15-omg-gossip-girl.jpg", body.getPayload().get(0).getImage());
    }
    
    @Test
    void whenGetProductListNotFoundData_thenEmptyPayloadIsReturned() {

        List<ProductDb> productList = new ArrayList<>();
        given(productService.getProductList(any(), any())).willReturn(productList);

        ResponseEntity<ProductResponse> response = unitUnderTest.getProductList(15, "M");
        ProductResponse body = response.getBody();
        assertEquals(200, body.getStatusCode());
        assertEquals("success", body.getMessage());
        assertTrue(body.getPayload().isEmpty());
    }
}