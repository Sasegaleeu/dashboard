package com.delivery.dashboard.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.delivery.dashboard.baskets.BasketsMapper;
import com.delivery.dashboard.domain.Baskets;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final BasketsMapper basketsMapper;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedDelay = 1000) // 매초마다 실행 (실제로는 필요한 간격에 맞게 조정해야 합니다.)
    public void sendBasketData() {
        List<Baskets> basketDataList = basketsMapper.getBasketDataWithLocation(); // DB에서 모든 바구니 데이터를 가져옵니다.

        for (Baskets basketData : basketDataList) {
            messagingTemplate.convertAndSend("/topic/basket-temperature-humidity", basketData);
        }
    }
}