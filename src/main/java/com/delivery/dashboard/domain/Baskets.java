package com.delivery.dashboard.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Baskets {
    private int basket_id;
    private int product_id;
    private int delivery_id;
    private int order_id;
    private BigDecimal temperature;
    private BigDecimal humidity;
    private BigDecimal delivery_latitude; // 딜리버리의 위도
    private BigDecimal delivery_longitude; // 딜리버리의 경도
    
}