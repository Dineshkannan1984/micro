package com.example.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path="/test")
public class Controller {

    @RequestMapping(path="/" , method=RequestMethod.GET)
    public String login(){

        log.info("first log");
        return "test controller testing123";
    }

}
