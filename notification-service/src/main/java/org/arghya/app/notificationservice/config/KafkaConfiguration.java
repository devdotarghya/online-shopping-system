/*

package org.arghya.app.notificationservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

import javax.annotation.PostConstruct;

*/
/**
 * In this class we'll add all the manual configuration required for Observability to
 * work.
 *//*


@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class KafkaConfiguration {

    private final ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory;

    @PostConstruct
    void setup() {
        this.concurrentKafkaListenerContainerFactory.getContainerProperties().setObservationEnabled(true);
    }

}
*/
