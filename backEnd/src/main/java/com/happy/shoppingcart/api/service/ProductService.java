package com.happy.shoppingcart.api.service;

import com.happy.shoppingcart.api.controller.domain.ProductResponse;
import com.happy.shoppingcart.common.entities.Shipping;
import com.happy.shoppingcart.common.entities.ShoppingCart;
import com.happy.shoppingcart.common.repo.ShippingRepo;
import com.happy.shoppingcart.common.repo.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProductService {
    @Autowired
    private ShoppingCartRepo shoppingRepos;
    private ShippingRepo shippingRepos;

    private ShoppingCart getCart(int id) {
        Optional<ShoppingCart> result = shoppingRepos.findById(id);
        return result.get();
    }

    private Shipping getShipping(int id) {
        Optional<Shipping> result = shippingRepos.findById(id);
        return result.get();
    }

//    public ProductResponse calculate(int shippingId, int cartId) {
//        ProductResponse response = new ProductResponse();
//        ShoppingCart dtCart = this.getCart(cartId);
//        Shipping dtShipping = this.getShipping(shippingId);
//        dtCart
//        return
//    }
}
