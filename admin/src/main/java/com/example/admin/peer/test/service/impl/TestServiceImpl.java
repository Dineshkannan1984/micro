package com.example.admin.peer.test.service.impl;

import com.example.admin.peer.test.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private ApplicationContext applicationContext;  

    @Override
    @CircuitBreaker(name = "peer", fallbackMethod = "fallback")
    public String getMessage(){
        RestTemplate restTemplate = applicationContext.getBean(RestTemplate.class);

        log.info("Service A Test log 21");

        String response = restTemplate.getForObject("http://test.default.svc.cluster.local:80/test/",String.class);

        // while(true);

        return "Hello  : Service A Test log" + " response : " + response;
    }

    @SuppressWarnings("unused")
    private String fallback(Exception e) {
        return "Fallback" + e.getMessage();
    }
}
