package com.example.admin.kafka.consumers;

import com.example.admin.serviceA.model.Testpojo;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Profile("!local")
public class ConsumerExample {

    @KafkaListener(topics = "topic1")
    void listener(Testpojo data) {
        log.info(data.toString());
    }
}
