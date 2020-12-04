package com.happy.shoppingcart.api.service;

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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@TestComponent
public class CalculateServiceTest {
    @InjectMocks
    private CalculateService calculateService;
    @Mock private ShoppingCartRepo shoppingCartRepo;
    @Mock private CurrencyRepo currencyRepo;
    @Mock private ProductTbRepo productTbRepo;
    @Mock private LoyaltyConfigRepo loyaltyConfigRepo;

    @Before
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void calculatePrice_success(){
        Currency currency = new Currency();
        currency.setExcRate(new BigDecimal(10));

        ProductTb product = new ProductTb();
        product.setPrice(new BigDecimal(25));

        LoyaltyConfig loyaltyConfig = new LoyaltyConfig();
        loyaltyConfig.setPointRate(100);

        List<LoyaltyConfig> listLoyalty = new ArrayList<>();
        listLoyalty.add(loyaltyConfig);

        ShoppingCart cart = new ShoppingCart();
        cart.setCartId(1);
        Mockito.when(shoppingCartRepo.saveAndFlush(Mockito.any(ShoppingCart.class))).thenReturn(cart);
        Mockito.when(productTbRepo.getOne(Mockito.anyInt())).thenReturn(product);
        Mockito.when(currencyRepo.findByCurrencyCode(Mockito.anyString())).thenReturn(currency);
        Mockito.when(loyaltyConfigRepo.findAll()).thenReturn(listLoyalty);

        CalculateReponse response = calculateService.calculatePrice(1234);

        Assertions.assertEquals(200, response.getHttpStatus());
        Assertions.assertEquals("0", response.getStatusCode());
        Assertions.assertEquals("success", response.getMessage());
        Assertions.assertEquals(new BigDecimal(250).setScale(3), response.getTotal());
        Assertions.assertEquals(2, response.getPoint());

    }
}
