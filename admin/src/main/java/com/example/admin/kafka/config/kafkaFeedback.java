package com.example.admin.kafka.config;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class kafkaFeedback {
    
    @Autowired 
    private KafkaTemplate<String,Object> kafkaTemplate;

    @PostConstruct
    public void init(){
        // start your monitoring in here
        kafkaTemplate.setProducerListener(new ProducerListener<String, Object>() {
                    @Override
                    public void onSuccess(ProducerRecord<String, Object> producerRecord, RecordMetadata recordMetadata) {
                        
                        log.info("ACK from ProducerListener message: {} offset:  {}",
                        producerRecord.value(),
                        recordMetadata.offset());
                    }
        
                    @Override
                    public void onError(ProducerRecord<String, Object> producerRecord, RecordMetadata recordMetadata,Exception e) {
                        
                        log.info("Failure from ProducerListener message: {}",producerRecord.value());
                    }
                });
    }
}
