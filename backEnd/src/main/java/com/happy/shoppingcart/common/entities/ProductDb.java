package com.happy.shoppingcart.common.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_db")
public class ProductDb {
    @Id
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "brand")
    private String brand;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private int age;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "img")
    private String img;
}