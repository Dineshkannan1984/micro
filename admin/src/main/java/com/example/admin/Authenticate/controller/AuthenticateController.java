package com.example.admin.Authenticate.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/admin")
public class AuthenticateController {

    
    private static Logger logger = LoggerFactory.getLogger(AuthenticateController.class);
    
    @RequestMapping(path="/login" , method=RequestMethod.GET)
    public String login(){

        logger.info("first log");
        return "login controller testing123";
    }
}
