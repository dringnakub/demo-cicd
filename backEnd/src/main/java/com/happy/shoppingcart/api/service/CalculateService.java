package com.happy.shoppingcart.api.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.happy.shoppingcart.api.controller.domain.CalculateReponse;
import com.happy.shoppingcart.api.controller.domain.CalculateResponsePayload;
import com.happy.shoppingcart.common.entities.Currency;
import com.happy.shoppingcart.common.entities.LoyaltyConfig;
import com.happy.shoppingcart.common.entities.ProductTb;
import com.happy.shoppingcart.common.entities.ShoppingCart;
import com.happy.shoppingcart.common.repo.CurrencyRepo;
import com.happy.shoppingcart.common.repo.LoyaltyConfigRepo;
import com.happy.shoppingcart.common.repo.ProductTbRepo;
import com.happy.shoppingcart.common.repo.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculateService {

    private String CURR_CODE = "USD";

    @Autowired private ShoppingCartRepo shoppingCartRepo;
    @Autowired private CurrencyRepo currencyRepo;
    @Autowired private ProductTbRepo productTbRepo;
    @Autowired private LoyaltyConfigRepo loyaltyConfigRepo;

    public CalculateReponse calculatePrice(int productId){
        int cartId = this.addShoppingCart(productId);
        Currency currency = currencyRepo.findByCurrencyCode(CURR_CODE);
        ProductTb product = productTbRepo.getOne(productId);
        BigDecimal total = product.getPrice().multiply(currency.getExcRate());
        LoyaltyConfig loyaltyConfig = loyaltyConfigRepo.findAll().get(0);
        BigDecimal pointRate = BigDecimal.valueOf(loyaltyConfig.getPointRate());
        BigDecimal point = total.divide(pointRate);
        CalculateReponse calculateReponse = new CalculateReponse();
        calculateReponse.setHttpStatus(200);
        calculateReponse.setStatusCode("0");
        calculateReponse.setMessage("success");
        calculateReponse.setPoint(point.intValue());
        calculateReponse.setTotal(total.setScale(3));
        calculateReponse.setCartId(cartId);
        CalculateResponsePayload calculateResponsePayload = new CalculateResponsePayload();
        calculateResponsePayload.setProductId(product.getProductId());
        calculateResponsePayload.setPrice(product.getPrice().setScale(2));
        calculateResponsePayload.setProductName(product.getProductName());
        List<CalculateResponsePayload> payload = new ArrayList<>();
        payload.add(calculateResponsePayload);
        calculateReponse.setPayload(payload);
        
        return calculateReponse;
    }

    private int addShoppingCart(int productId){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setProductId(productId);
        shoppingCart = shoppingCartRepo.saveAndFlush(shoppingCart);

        return shoppingCart.getCartId();
    }

}