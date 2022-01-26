package com.example.admin.serviceA.controller;

import com.example.admin.kafka.producers.ProducerExample;
import com.example.admin.peer.test.service.TestService;
import com.example.admin.serviceA.model.Testpojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path="/ServiceA")
@Slf4j
public class ServiceAController {

    @Autowired
    TestService testService;

    @Autowired
    ProducerExample producerExample;
    
    @RequestMapping(path="/test" , method=RequestMethod.GET)
    @Cacheable(value = "testCache", key = "'test'")
    public Testpojo getMessage(){
        Testpojo testpojo = new Testpojo();
        producerExample.sendMessage(testpojo, "topic1");
        log.info(testService.getMessage());
        return testpojo;
    }
}
