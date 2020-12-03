package com.happy.shoppingcart.api.service;

import com.happy.shoppingcart.common.entities.ShoppingCart;
import com.happy.shoppingcart.common.repo.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculateService {
    @Autowired private ShoppingCartRepo shoppingCartRepo;
    public void addShoppingCart(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartId(100);
        shoppingCart.setProductId(1234);
        shoppingCartRepo.save(shoppingCart);
    }        

}