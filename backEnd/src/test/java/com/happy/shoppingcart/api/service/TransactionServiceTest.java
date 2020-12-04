package com.happy.shoppingcart.api.service;

import com.happy.shoppingcart.api.controller.domain.TransactionRequest;
import com.happy.shoppingcart.common.entities.*;
import com.happy.shoppingcart.common.repo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService unitUnderTest;
    @Mock
    private ShippingRepo shippingRepo;
    @Mock
    private ShoppingCartRepo shoppingCartRepo;
    @Mock
    private ProductTbRepo productTbRepo;
    @Mock
    private CurrencyRepo currencyRepo;
    @Mock
    private TransactionRepo transactionRepo;
    @Mock
    private LoyaltyConfigRepo loyaltyConfigRepo;
    
    @Test
    void calculateAndCreateTransaction() {

        TransactionRequest requestBody = new TransactionRequest();
        requestBody.setAddress1("123");
        requestBody.setAddress2("Ratchadapisek R. Dindang Bangok");
        requestBody.setCountry("Thailand");
        requestBody.setPostCode(10120);
        requestBody.setMobileNumber("0863873913");
        requestBody.setCartId(1);
        requestBody.setShippingId(2);

        Currency currency = new Currency();
        currency.setCurrencyCode("USD");
        currency.setExcRateDate(LocalDateTime.of(2020, 11, 29, 14, 40, 0));
        currency.setExcRate(BigDecimal.valueOf(30.30));
        given(currencyRepo.findByCurrencyCode("USD")).willReturn(currency);

        Shipping shipping = new Shipping();
        shipping.setShippingId(2);
        shipping.setShippingName("Kerry");
        shipping.setShippingRate(BigDecimal.valueOf(40.00));
        given(shippingRepo.findById(2)).willReturn(Optional.of(shipping));
        
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartId(1);
        shoppingCart.setProductId(15);
        given(shoppingCartRepo.findById(1)).willReturn(Optional.of(shoppingCart));

        ProductTb productTb = new ProductTb();
        productTb.setProductId(15);
        productTb.setProductName("OMG-Gossip Girl");
        productTb.setPrice(BigDecimal.valueOf(24.95));
        given(productTbRepo.findById(15)).willReturn(Optional.of(productTb));
        
        LoyaltyConfig loyaltyConfig = new LoyaltyConfig();
        loyaltyConfig.setRowId(1);
        loyaltyConfig.setPointRate(100);
        given(loyaltyConfigRepo.findById(1)).willReturn(Optional.of(loyaltyConfig));
        
        Transaction savedTransaction = new Transaction();
        savedTransaction.setTransactionId(10);
        given(transactionRepo.save(any(Transaction.class))).willReturn(savedTransaction);
        
        int transactionId = unitUnderTest.createTransaction(requestBody);

        assertEquals(10, transactionId);        
        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionRepo).save(transactionCaptor.capture());
        Transaction transaction = transactionCaptor.getValue();
        assertEquals("123", transaction.getAddr1());
        assertEquals("Ratchadapisek R. Dindang Bangok", transaction.getAddr2());
        assertEquals("10120", transaction.getAddr3());
        assertEquals("Thailand", transaction.getCountry());
        assertEquals("0863873913", transaction.getMobileNumber());
        assertEquals(2, transaction.getShippingId());
        assertEquals(1, transaction.getCartId());
        assertEquals(BigDecimal.valueOf(795.985), transaction.getTotal()); // Total + Shipping fee.
        assertEquals(7, transaction.getPoint());
        assertEquals(40, transaction.getShippingFee());
        assertEquals("created", transaction.getStatus());
    }
    
}