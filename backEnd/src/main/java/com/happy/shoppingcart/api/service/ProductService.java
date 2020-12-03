package com.happy.shoppingcart.api.service;

import com.happy.shoppingcart.api.controller.domain.ProductResponsePayload;
import com.happy.shoppingcart.common.entities.ProductDb;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    
    public List<ProductDb> getProductList(@Nullable Integer age, @Nullable String gender) {
        return null;
    }
}
