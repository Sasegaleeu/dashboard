package com.delivery.dashboard.receiver;

import org.apache.ibatis.annotations.Mapper;

import com.delivery.dashboard.domain.Deliveries;

@Mapper
public interface DeliveriesMapper {
    void updateDeliveryLocation(Deliveries deliveries);
}
