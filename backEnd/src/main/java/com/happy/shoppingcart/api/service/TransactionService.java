package com.happy.shoppingcart.api.service;

import com.happy.shoppingcart.api.controller.domain.TransactionGetResponse;
import com.happy.shoppingcart.api.controller.domain.TransactionRequest;
import com.happy.shoppingcart.common.entities.Transaction;
import com.happy.shoppingcart.common.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

    public TransactionGetResponse getTransactionById(int id) {
        Optional<Transaction> result = transactionRepo.findById(id);
        Transaction res = result.get();
        TransactionGetResponse transactionGetResponse = new TransactionGetResponse(
                res.getPoint(),
                res.getTotal(),
                res.getShippingFee()
        );
        return transactionGetResponse;
    }

    public int createTransaction(TransactionRequest requestBody) {
        return 0;
    }
}
