package com.delivery.dashboard.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private int order_id;
    private String customer_name;
    private int quantity_ordered;
    private int total_price;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Timestamp date_time;
    
}