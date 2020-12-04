package com.happy.shoppingcart.api.controller.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionCreateResponse {
    @JsonProperty("transaction_id")
    private int transactionId;
}
