package com.happy.shoppingcart.api.service.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisaDetail {
    private int transaction_id;
    private String card_number;
    private String card_holder;
    private int cvv;
    private String expire_month;
    private String expireyear;


}
