package com.delivery.dashboard.baskets;

import java.util.List;

import com.delivery.dashboard.domain.Baskets;

public interface BasketsService {
        List<Baskets> getBasketDataWithLocation();
}
