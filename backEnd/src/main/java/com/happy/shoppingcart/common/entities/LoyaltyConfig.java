package com.happy.shoppingcart.common.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "loyalty_config")
public class LoyaltyConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_id")
    private int rowId;

    @Column(name = "point_rate")
    private int pointRate;

    @Column(name = "created_at")
    private LocalDateTime createAt;
}
