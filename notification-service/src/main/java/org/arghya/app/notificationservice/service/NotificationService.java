package org.arghya.app.notificationservice.service;

import lombok.extern.slf4j.Slf4j;
import org.arghya.app.notificationservice.event.OrderPlacedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class NotificationService {

    @KafkaListener(topics="notificationTopic", groupId = "notificationId")
    public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
        log.info("Received notification for order - {}", orderPlacedEvent.getOrderNumber());
    }
}
