package org.arghya.app.notificationservice;

import lombok.extern.slf4j.Slf4j;
import org.arghya.app.notificationservice.event.OrderPlacedEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

   /* @KafkaListener(topics={"notificationTopic"})
    public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
        log.info("Received notification for order - {}", orderPlacedEvent.getOrderNumber());
    }*/

    /*@KafkaListener(topics="notificationTopic", groupId = "notificationId")
    public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
        log.info("Received notification for order - {}", orderPlacedEvent.getOrderNumber());
    }*/
}
