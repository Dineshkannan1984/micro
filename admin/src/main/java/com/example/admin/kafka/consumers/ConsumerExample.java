package com.example.admin.kafka.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConsumerExample {

    @KafkaListener(topics = "topic1",groupId = "group-2")
    void listener(String data) {
        log.info(data);
    }
}
