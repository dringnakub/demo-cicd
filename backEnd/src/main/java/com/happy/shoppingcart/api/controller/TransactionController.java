package com.happy.shoppingcart.api.controller;

import com.happy.shoppingcart.api.controller.domain.TransactionResponse;
import com.happy.shoppingcart.api.service.VisaService;
import com.happy.shoppingcart.api.service.domain.VisaDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import com.happy.shoppingcart.api.controller.domain.TransactionCreateResponse;
import com.happy.shoppingcart.api.controller.domain.TransactionGetResponse;
import com.happy.shoppingcart.api.controller.domain.TransactionRequest;
import com.happy.shoppingcart.api.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    VisaService visaService;
    @Autowired
    private TransactionService transactionService;

    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> getVisaDetail(@RequestBody VisaDetail request )throws Exception {

        //call verifyVisa
        BigDecimal total = transactionService.getTotalFromTransaction(request.getTransactionId());
        String visaApprove = visaService.verifyVisa(request,total);
        //call sentToKerry
        String trackingNumber = transactionService.createTrackingNumber(request);
        TransactionResponse response = new TransactionResponse("12345", trackingNumber);
        //update transaction
        transactionService.updateTransaction(request.getTransactionId());
        //update product quantity
        transactionService.updateProductQuantity(123);
        ResponseEntity<TransactionResponse> responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<TransactionGetResponse> getTransactionByID (@RequestParam("transaction_id") int id) {
        // Test cicd test 1 //
        TransactionGetResponse response = transactionService.getTransactionById(id);
        return  ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<TransactionCreateResponse> createTransaction(@RequestBody TransactionRequest body) {

        TransactionCreateResponse response = new TransactionCreateResponse();
        response.setTransactionId(transactionService.createTransaction(body));
        return ResponseEntity.ok(response);
    }
}
