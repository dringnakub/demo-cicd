package com.happy.shoppingcart.api.controller;


import com.happy.shoppingcart.api.controller.domain.TransactionCreateResponse;
import com.happy.shoppingcart.api.controller.domain.TransactionGetResponse;
import com.happy.shoppingcart.api.controller.domain.TransactionRequest;
import com.happy.shoppingcart.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    //@PutMapping
    //public ResponseEntity<TransactionResponse> getVisaDetail(@RequestHeader(name = "X-Correlation-Id", required = true) String correlationId,
     //                                                 @RequestBody VisaDetail request )throws Exception {
        //TransactionResponse response = new TransactionResponse;
        //ResponseEntity<TransactionResponse> responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        //return responseEntity;
    //}

    @GetMapping
    public ResponseEntity<TransactionGetResponse> getTransactionByID (@RequestParam("transaction_id") int id) {
        var response = transactionService.getTransactionById(id);
        return  ResponseEntity.ok().body(response);
    }
    
    @PostMapping
    public ResponseEntity<TransactionCreateResponse> createTransaction(@RequestBody TransactionRequest body) {
        
        TransactionCreateResponse response = new TransactionCreateResponse();
        response.setTransactionId(transactionService.createTransaction(body));
        return ResponseEntity.ok(response);
    }
}
