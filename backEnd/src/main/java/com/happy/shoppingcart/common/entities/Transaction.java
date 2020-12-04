package com.happy.shoppingcart.common.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @Column(name = "transaction_id")
    private int transactionId;
    @Column(name = "addr1")
    private String addr1;
    @Column(name = "addr2")
    private String addr2;
    @Column(name = "addr3")
    private String addr3;
    @Column(name = "country")
    private String country;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "shipping_id")
    private int shippingId;
    @Column(name = "cart_id")
    private int cartId;
    @Column(name = "total")
    private BigDecimal total;
    @Column(name = "point")
    private int point;
    @Column(name = "post_code")
    private String postCode;
    @Column(name = "shipping_fee")
    private int shippingFee;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @Column(name = "status")
    private String status;
}
