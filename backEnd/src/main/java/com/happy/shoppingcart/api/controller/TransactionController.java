package com.happy.shoppingcart.api.controller;


import com.happy.shoppingcart.api.controller.domain.TransactionResponse;
import com.happy.shoppingcart.api.service.VisaService;
import com.happy.shoppingcart.api.service.domain.VisaDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    VisaService visaService;

    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> getVisaDetail(@RequestBody VisaDetail request )throws Exception {

        //call verifyVisa

        String visaApprove = visaService.verifyVisa(request);
        //call sentToKerry

        TransactionResponse response = new TransactionResponse(visaApprove,"123456");
        ResponseEntity<TransactionResponse> responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        return responseEntity;
    }

}
