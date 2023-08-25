package com.delivery.dashboard.configuration;

import com.delivery.dashboard.domain.Deliveries;

public interface WebSocketService {
    void notifyDashboardUpdate(Deliveries deliveries);
}
