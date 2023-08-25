package com.delivery.dashboard.configuration;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.delivery.dashboard.domain.Deliveries;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WebSocketServiceImpl implements WebSocketService {

    private final SimpMessageSendingOperations messagingTemplate;
    
    @Override
    public void notifyDashboardUpdate(Deliveries deliveries) {
        messagingTemplate.convertAndSend("/topic/location", deliveries);
    }
}
