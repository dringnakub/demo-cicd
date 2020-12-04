package com.happy.shoppingcart.api.controller.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TransactionGetResponse {
    private int point;
    private double total;

    @JsonProperty("shopping_fee")
    private double shoppingFee;

    public TransactionGetResponse(int point, double total, double shoppingFee) {
        this.point = point;
        this.total = total;
        this.shoppingFee = shoppingFee;

    }
}
