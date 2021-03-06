package com.happy.shoppingcart.api.service;

import com.happy.shoppingcart.api.controller.domain.ProductResponse;
import com.happy.shoppingcart.common.entities.*;
import com.happy.shoppingcart.common.repo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService unitUnderTest;
    @Mock
    private ProductTbRepo ProductTbRepos;
    @Mock
    private ShoppingCartRepo shoppingRepos;
    @Mock
    private ShippingRepo shippingRepo;
    @Mock
    private LoyaltyConfigRepo loyaltyConfigRepo;
    @Mock
    private CurrencyRepo currencyRepo;

    @Test
    void getProductListWhenInputAgeAndGenderIsNull_resultIsReturnedByFindAll() {

        List<ProductTb> ProductTbList = new ArrayList<>();
        ProductTb ProductTb = new ProductTb();
        ProductTb.setProductId(15);
        ProductTb.setProductName("OMG-Gossip Girl");
        ProductTbList.add(ProductTb);
        given(ProductTbRepos.findAll()).willReturn(ProductTbList);

        List<ProductTb> resultList = this.unitUnderTest.getProductList(null, null);

        assertEquals(1, resultList.size());
        assertEquals(15, resultList.get(0).getProductId());
        assertEquals("OMG-Gossip Girl", resultList.get(0).getProductName());
    }

    @Test
    void getProductListWhenInputOnlyAge_resultIsReturnedByFindByAge() {

        List<ProductTb> ProductTbList = new ArrayList<>();
        ProductTb ProductTb = new ProductTb();
        ProductTb.setProductId(15);
        ProductTb.setProductName("OMG-Gossip Girl");
        ProductTbList.add(ProductTb);
        given(ProductTbRepos.findByAge(15)).willReturn(ProductTbList);

        List<ProductTb> resultList = this.unitUnderTest.getProductList(15, null);

        assertEquals(1, resultList.size());
        assertEquals(15, resultList.get(0).getProductId());
        assertEquals("OMG-Gossip Girl", resultList.get(0).getProductName());
    }

    @Test
    void getProductListWhenInputOnlyGender_resultIsReturnedByFindByGender() {

        List<ProductTb> ProductTbList = new ArrayList<>();
        ProductTb ProductTb = new ProductTb();
        ProductTb.setProductId(15);
        ProductTb.setProductName("OMG-Gossip Girl");
        ProductTbList.add(ProductTb);
        given(ProductTbRepos.findByGender("male")).willReturn(ProductTbList);

        List<ProductTb> resultList = this.unitUnderTest.getProductList(null, "male");

        assertEquals(1, resultList.size());
        assertEquals(15, resultList.get(0).getProductId());
        assertEquals("OMG-Gossip Girl", resultList.get(0).getProductName());
    }

    @Test
    void getProductListWhenInputAgeAndGender_resultIsReturnedByFindByAgeAndGender() {

        List<ProductTb> ProductTbList = new ArrayList<>();
        ProductTb ProductTb = new ProductTb();
        ProductTb.setProductId(15);
        ProductTb.setProductName("OMG-Gossip Girl");
        ProductTbList.add(ProductTb);
        given(ProductTbRepos.findByAgeAndGender(15, "male")).willReturn(ProductTbList);

        List<ProductTb> resultList = this.unitUnderTest.getProductList(15, "male");

        assertEquals(1, resultList.size());
        assertEquals(15, resultList.get(0).getProductId());
        assertEquals("OMG-Gossip Girl", resultList.get(0).getProductName());
    }

    @Test
    void getCartListWhenInputId_resultIsReturnedByFindById() {

        ShoppingCart shoppingCart = new ShoppingCart();
        Shipping shipping = new Shipping();
        ProductTb productTb = new ProductTb();
        LoyaltyConfig loyalty = new LoyaltyConfig();
        Currency currency = new Currency();

        productTb.setProductId(15);
        productTb.setProductName("OMG-Gossip Girl");
        productTb.setPrice(BigDecimal.valueOf(24.95));
        shoppingCart.setCartId(1);
        shoppingCart.setProductId(15);
        shipping.setShippingId(1);
        shipping.setShippingName("Kerry");
        shipping.setShippingRate(BigDecimal.valueOf(40));
        loyalty.setRowId(1);
        loyalty.setPointRate(100);
        currency.setCurrencyCode("USB");
        currency.setExcRate(BigDecimal.valueOf(30.30));

        given(ProductTbRepos.findById(15)).willReturn(Optional.of(productTb));
        given(shoppingRepos.findById(1)).willReturn(Optional.of(shoppingCart));
        given(shippingRepo.findById(1)).willReturn(Optional.of(shipping));
        given(loyaltyConfigRepo.findById(1)).willReturn(Optional.of(loyalty));
        given(currencyRepo.findByCurrencyCode("USB")).willReturn(currency);


        ProductResponse result = this.unitUnderTest.calculateAll(1, 1);

        assertEquals(1, result.getCartId());
        assertEquals(BigDecimal.valueOf(7), result.getPoint());
        assertEquals(BigDecimal.valueOf(795.985), result.getTotalWithShip());
        assertEquals(15, result.getPayload().get(0).getProductId());
        assertEquals("OMG-Gossip Girl", result.getPayload().get(0).getProductName());
    }
}