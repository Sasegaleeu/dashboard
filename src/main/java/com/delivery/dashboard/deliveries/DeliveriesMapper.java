package com.delivery.dashboard.deliveries;

import org.apache.ibatis.annotations.Mapper;

import com.delivery.dashboard.domain.Deliveries;

@Mapper
public interface DeliveriesMapper {
    void updateLocation(Deliveries deliveries);
}
