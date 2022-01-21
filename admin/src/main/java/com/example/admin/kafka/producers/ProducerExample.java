package com.example.admin.kafka.producers;

import com.example.admin.configuration.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProducerExample {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message, String topicName) {
        kafkaTemplate.send(topicName, message);
    }
}
