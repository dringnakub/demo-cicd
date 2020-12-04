package com.happy.shoppingcart.common.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "shipping_tb")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_id")
    private int shippingId;

    @Column(name = "shipping_rate")
    private BigDecimal shippingRate;

    @Column(name = "shipping_name")
    private String shippingName;
}
