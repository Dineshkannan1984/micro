package com.example.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/test")
public class Controller {
    private static Logger logger = LoggerFactory.getLogger(Controller.class);

    @RequestMapping(path="/" , method=RequestMethod.GET)
    public String login(){

        logger.info("first log");
        return "test controller testing123";
    }

}
