package com.happy.shoppingcart.api.service.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisaDetail {
    @JsonProperty("transaction_id")
    private int transactionId;
    @JsonProperty("card_number")
    private String cardNumber;
    @JsonProperty("card_holder")
    private String cardHolder;
    @JsonProperty("cvv")
    private int cvv;
    @JsonProperty("expire_month")
    private String expireMonth;
    @JsonProperty("expireyear")
    private String expireYear;


}
