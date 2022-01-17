package com.example.admin.serviceA.controller;

import com.example.admin.kafka.producers.ProducerExample;
import com.example.admin.peer.test.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/ServiceA")
public class ServiceAController {

    @Autowired
    TestService testService;

    @Autowired
    ProducerExample producerExample;
    
    @RequestMapping(path="/test" , method=RequestMethod.GET)
    public String getMessage(){
        producerExample.sendMessage("message", "topic1");
        return testService.getMessage();
    }
}
