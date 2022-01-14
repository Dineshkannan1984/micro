package com.example.admin.serviceA.controller;

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
    
    @RequestMapping(path="/test" , method=RequestMethod.GET)
    public String getMessage(){
        return testService.getMessage();
    }
}
