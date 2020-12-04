package com.happy.shoppingcart.api.service;

import com.happy.shoppingcart.api.controller.domain.ProductPayload;
import com.happy.shoppingcart.api.controller.domain.ProductResponse;
import com.happy.shoppingcart.common.entities.*;
import com.happy.shoppingcart.common.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ShoppingCartRepo shoppingRepos;
    @Autowired
    private ShippingRepo shippingRepos;
    @Autowired
    private ProductTbRepo productTbRepos;
    @Autowired
    private LoyaltyConfigRepo loyaltyConfigRepo;
    @Autowired
    private CurrencyRepo currencyRepo;

    private ShoppingCart getCart(int id) {
        Optional<ShoppingCart> result = shoppingRepos.findById(id);
        return result.get();
    }

    private Shipping getShipping(int id) {
        Optional<Shipping> result = shippingRepos.findById(id);
        return result.get();
    }

    private ProductTb getProductListById(int productId) {
        Optional<ProductTb> result = productTbRepos.findById(productId);
        return result.get();
    }

    private LoyaltyConfig getLoyalty(int id) {
        Optional<LoyaltyConfig> result = loyaltyConfigRepo.findById(id);
        return result.get();
    }

    private Currency getCurrency(String currencyCode) {
        Currency result = currencyRepo.findByCurrencyCode(currencyCode);
        return result;
    }

    public ProductResponse calculateAll(int shippingId, int cartId) {
        ProductResponse response = new ProductResponse();
        ProductPayload payLoad = new ProductPayload();
        ShoppingCart dtCart = this.getCart(cartId);
        Shipping dtShipping = this.getShipping(shippingId);
        ProductTb dtProduct = this.getProductListById(dtCart.getProductId());
        LoyaltyConfig dtLoyalty = this.getLoyalty(1);
        Currency currencyTHB = this.getCurrency("USB");
        BigDecimal thbPrice = dtProduct.getPrice().multiply(currencyTHB.getExcRate());
        BigDecimal pointTotal = thbPrice.divide(BigDecimal.valueOf(dtLoyalty.getPointRate()))
                .setScale(0, RoundingMode.FLOOR);
        BigDecimal totalWithShipping = thbPrice.add(dtShipping.getShippingRate());
        payLoad.setProductId(dtProduct.getProductId());
        payLoad.setProductName(dtProduct.getProductName());
        payLoad.setPrice(thbPrice.doubleValue());

        response.setCartId(dtCart.getCartId());
        response.setPoint(pointTotal);
        response.setTotalWithShip(totalWithShipping);
        response.setPayload(Arrays.asList(payLoad));
        return response;
    }

    public List<ProductTb> getProductList(@Nullable Integer age, @Nullable String gender) {

        if (age != null && gender != null) {
            return this.productTbRepos.findByAgeAndGender(age, gender);
        }
        if (age == null && gender == null) {
            return this.productTbRepos.findAll();
        }
        if (age != null) {
            return this.productTbRepos.findByAge(age);
        }
        return this.productTbRepos.findByGender(gender);
    }


}
