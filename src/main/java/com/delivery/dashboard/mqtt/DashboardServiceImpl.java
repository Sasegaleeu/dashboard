package com.delivery.dashboard.mqtt;

import lombok.extern.slf4j.Slf4j;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Slf4j
@Service
public class DashboardServiceImpl implements DashboardService {

    @Value("${mqtt.broker-url}")
    private String mqttBrokerUrl;

    @Value("${mqtt.client-id}")
    private String clientId;

    @Value("${mqtt.topic}")
    private String topic;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    // 빈 초기화 후 실행되는 메서드
    @PostConstruct
    public void init() {
        subscribe();
    }

    // MQTT 구독을 처리하는 메서드
    public void subscribe() {
        MqttClient client = null;
        try {
            // MQTT 클라이언트 생성
            client = new MqttClient(mqttBrokerUrl, clientId);

            // MQTT 연결 옵션 설정
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setUserName(username);
            options.setPassword(password.toCharArray());

            // MQTT 브로커에 연결
            client.connect(options);

            // 특정 토픽에 대한 구독 및 메시지 콜백 설정
            client.subscribe(topic, 1, (arrivedTopic, message) -> {
                handleMessage(arrivedTopic, message);
            });
        } catch (MqttException e) {
            handleError(e);
        }
    }

    // 메시지 수신 시 호출되는 메서드
    private void handleMessage(String topic, MqttMessage message) {
        String payload = new String(message.getPayload());
        log.info("Message received from topic {}: {}", topic, payload);
    }

    // MQTT 관련 오류 처리 메서드
    private void handleError(MqttException e) {
        log.error("Error while subscribing to topic: " + e.getMessage(), e);
    }
}
