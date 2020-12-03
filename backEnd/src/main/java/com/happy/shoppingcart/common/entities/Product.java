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
@Table(name = "product_tb")
public class Product {
    
    @Id
    @Column(name = "product_id", nullable = false)
    private Integer productId;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "brand", nullable = false)
    private String brand;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private Integer age;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "img")
    private String img;
}
