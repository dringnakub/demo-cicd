package com.happy.shoppingcart.api.controller;

import com.happy.shoppingcart.api.controller.domain.TransactionCreateResponse;
import com.happy.shoppingcart.api.controller.domain.TransactionRequest;
import com.happy.shoppingcart.api.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @InjectMocks
    private TransactionController unitUnderTest;
    @Mock
    private TransactionService transactionService;
    
    @Test
    void whenCreateTransactionSuccess_shouldReturnTransactionId() {
        
        given(transactionService.createTransaction(any(TransactionRequest.class))).willReturn(5);
        
        ResponseEntity<TransactionCreateResponse> response = unitUnderTest.createTransaction(new TransactionRequest());
        
        assertEquals(5, response.getBody().getTransactionId());
    }
}