package com.delivery.dashboard.baskets;

import java.util.List;

import org.springframework.stereotype.Service;

import com.delivery.dashboard.domain.Baskets;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class BasketsServiceImpl implements BasketsService {

    private final BasketsMapper basketsMapper;

    @Override
    public List<Baskets> getBasketDataWithLocation() {
        return basketsMapper.getBasketDataWithLocation();
    }
    
}
