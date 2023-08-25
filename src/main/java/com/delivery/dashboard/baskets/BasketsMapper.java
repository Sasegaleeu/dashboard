package com.delivery.dashboard.baskets;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.delivery.dashboard.domain.Baskets;


@Mapper
public interface BasketsMapper {
    List<Baskets> getBasketDataWithLocation();
}
