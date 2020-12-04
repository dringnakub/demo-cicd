package com.happy.shoppingcart.api.controller.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponse {
    private String order_no;
    private String tracking_no;

    public TransactionResponse(String order_no, String tracking_no) {
        this.order_no = order_no;
        this.tracking_no = tracking_no;
    }


}
