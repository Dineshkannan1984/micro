package com.example.admin.ServiceA.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path="/admin/ServiceA")
public class ServiceAController {
    private static Logger logger = LoggerFactory.getLogger(ServiceAController.class);

    @Autowired
    private ApplicationContext applicationContext;  
    
    @RequestMapping(path="/test" , method=RequestMethod.GET)
    public String login(){
        RestTemplate restTemplate = applicationContext.getBean(RestTemplate.class);

        logger.info("Service A Test log");

        // String response = "";
        String response = restTemplate.getForObject("http://test.default.svc.cluster.local:80/test/",String.class);

        return "Service A Test log" + " response : " + response;
    }
}
