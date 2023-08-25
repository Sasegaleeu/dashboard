package com.delivery.dashboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    private int detail_id;
    private int order_id;
    private int product_id;
    private int quantity;
    private int price;
}
