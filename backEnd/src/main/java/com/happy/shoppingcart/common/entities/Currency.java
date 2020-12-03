package com.happy.shoppingcart.common.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @Column(name = "row_id")
    private String rowId;

    @Column(name = "exc_rate_date")
    private LocalDateTime excRateDate;

    @Column(name = "exc_rate")
    private BigDecimal excRate;

    @Column(name = "currencyCode")
    private String currencyCode;
    
}