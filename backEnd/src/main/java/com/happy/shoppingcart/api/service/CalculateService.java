package com.happy.shoppingcart.api.service;

import java.math.BigDecimal;

import com.happy.shoppingcart.common.entities.Currency;
import com.happy.shoppingcart.common.entities.LoyaltyConfig;
import com.happy.shoppingcart.common.entities.ProductDb;
import com.happy.shoppingcart.common.entities.ShoppingCart;
import com.happy.shoppingcart.common.repo.CurrencyRepo;
import com.happy.shoppingcart.common.repo.LoyaltyConfigRepo;
import com.happy.shoppingcart.common.repo.ProductDbRepo;
import com.happy.shoppingcart.common.repo.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculateService {

    private String CURR_CODE = "USD";

    @Autowired private ShoppingCartRepo shoppingCartRepo;
    @Autowired private CurrencyRepo currencyRepo;
    @Autowired private ProductDbRepo productDbRepo;
    @Autowired private LoyaltyConfigRepo loyaltyConfigRepo;

    public void calculatePrice(int productId, int cartId){
        this.addShoppingCart(productId);
        Currency currency = currencyRepo.findByCurrencyCode(CURR_CODE);
        ProductDb product = productDbRepo.getOne(productId);
        BigDecimal total = product.getPrice().multiply(currency.getExcRate());
        LoyaltyConfig loyaltyConfig = loyaltyConfigRepo.findAll().get(0);
        BigDecimal pointRate = BigDecimal.valueOf(loyaltyConfig.getPointRate());
        BigDecimal point = total.divide(pointRate);
        
    }

    private void addShoppingCart(int productId){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setProductId(productId);
        shoppingCartRepo.save(shoppingCart);
    }

}