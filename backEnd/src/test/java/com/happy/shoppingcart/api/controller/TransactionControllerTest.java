package com.happy.shoppingcart.api.controller;

import com.happy.shoppingcart.api.controller.domain.TransactionCreateResponse;
import com.happy.shoppingcart.api.controller.domain.TransactionGetResponse;
import com.happy.shoppingcart.api.controller.domain.TransactionRequest;
import com.happy.shoppingcart.api.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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

    @Test
    void whenGetTransactionSuccess_shouldReturnPayload() {

        given(transactionService.getTransactionById(anyInt())).willReturn(new TransactionGetResponse(1,23,3));

        ResponseEntity<TransactionGetResponse> response = unitUnderTest.getTransactionByID(0);

        assertEquals(1, response.getBody().getPoint());
        assertEquals(23, response.getBody().getTotal());
        assertEquals(3, response.getBody().getShoppingFee());
    }
}

