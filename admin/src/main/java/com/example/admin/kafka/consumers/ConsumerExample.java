package com.example.admin.kafka.consumers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@ConditionalOnExpression("!'${service.environment}'.equalsIgnoreCase('local')")
public class ConsumerExample {

    @KafkaListener(topics = "topic1",groupId = "group-2")
    void listener(String data) {
        log.info(data);
    }
}
