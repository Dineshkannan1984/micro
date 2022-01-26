package com.example.admin.aop;

import com.example.admin.configuration.Config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class kafkaLocal {

    @Autowired
    Config config;

    @Around("execution(* com.example.admin..kafka.producers..*.*(..))")
    public Object profileKafkaProducers(ProceedingJoinPoint proceedingJoinPoint) throws Throwable 
    {
        if (config.getActiveProfile() != null && config.getActiveProfile().equalsIgnoreCase("local"))
        {
            log.info("message pushed to kafka topic");
        }
        else
        {
            final StopWatch stopWatch = new StopWatch();
          
            //Measure method execution time
            stopWatch.start();
            Object result = proceedingJoinPoint.proceed();
            stopWatch.stop();

            //Log method execution time
            log.info("Kafka topic push time :: {} ms",stopWatch.getTotalTimeMillis());

            return result;
        }
        return null;
    }
}
