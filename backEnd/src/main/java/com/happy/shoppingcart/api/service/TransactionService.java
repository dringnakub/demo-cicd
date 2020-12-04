package com.happy.shoppingcart.api.service;

import com.happy.shoppingcart.api.controller.domain.*;
import com.happy.shoppingcart.api.service.domain.VisaDetail;
import com.happy.shoppingcart.common.entities.Currency;
import com.happy.shoppingcart.common.entities.LoyaltyConfig;
import com.happy.shoppingcart.common.entities.ProductTb;
import com.happy.shoppingcart.common.entities.Transaction;
import com.happy.shoppingcart.common.repo.ProductTbRepo;
import com.happy.shoppingcart.common.repo.TransactionRepo;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private ProductTbRepo productTbRepo;

    private TrackingService trackingService;

    public TransactionGetResponse getTransactionById(int id) {
        Optional<Transaction> result = transactionRepo.findById(id);
        Transaction res = result.get();
        TransactionGetResponse transactionGetResponse = new TransactionGetResponse(
                res.getPoint(),
                res.getTotal().doubleValue(),
                res.getShippingFee()
        );
        return transactionGetResponse;
    }

    public int createTransaction(TransactionRequest requestBody) {
        return 0;
    }
    
    public BigDecimal getTotalFromTransaction(int tnxId){
        Transaction transaction = transactionRepo.getOne(tnxId);
        BigDecimal total = transaction.getTotal();
        return total;
    }

    public void updateTransaction(int tnxId){
        Transaction transaction = transactionRepo.getOne(tnxId);
        transaction.setStatus("success");
        transactionRepo.save(transaction);
    }

    public void updateProductQuantity(int productId){
        ProductTb productTb = productTbRepo.getOne(productId);
        int quantity = productTb.getQuantity()-1;
        productTb.setQuantity(quantity);
        productTbRepo.save(productTb);
    }

    public String createTrackingNumber(VisaDetail requestBody) throws JSONException {
        Optional<Transaction> transaction = transactionRepo.findById(requestBody.getTransactionId());
        log.info("Result [{}]", transaction.get());
        String trackingNumber = trackingService.createTrackingNumber(transaction.get().getAddr1()
                , transaction.get().getAddr2()
                , transaction.get().getPostCode()
                , transaction.get().getCountry()
                , transaction.get().getMobileNumber());
        return trackingNumber;
    }

}
