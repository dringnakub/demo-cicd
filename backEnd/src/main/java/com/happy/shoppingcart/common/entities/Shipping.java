package com.happy.shoppingcart.common.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "shipping_tb")
public class Shipping {
    @Id
    @Column(name = "shipping_id")
    private int shippingId;

    @Column(name = "shipping_rate")
    private int shippingRate;

    @Column(name = "shipping_name")
    private int shippingName;
}
