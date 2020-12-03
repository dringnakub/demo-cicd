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
@Table(name = "loyalty_config")
public class LoyaltyConfig {
    @Id
    @Column(name = "row_id")
    private int rowId;

    @Column(name = "point_rate")
    private int pointRate;

    @Column(name = "created_at")
    private DateTime createAt;
}
