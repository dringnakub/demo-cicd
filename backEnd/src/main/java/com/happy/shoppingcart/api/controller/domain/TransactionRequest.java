package com.happy.shoppingcart.api.controller.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionRequest {
    private String address1;
    private String address2;
    @JsonProperty("post_code")
    private int postCode;
    private String country;
    @JsonProperty("mobile_number")
    private String mobileNumber;
    @JsonProperty("shipping_id")
    private int shippingId;
}
