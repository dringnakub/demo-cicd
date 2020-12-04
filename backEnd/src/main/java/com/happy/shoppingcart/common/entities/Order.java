package com.happy.shoppingcart.common.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "order_tb")
public class Order {
    @Id
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "trans_id")
    private int transId;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "summary_amount")
    private BigDecimal summaryAmount;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
