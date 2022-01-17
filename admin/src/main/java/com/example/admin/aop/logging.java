package com.example.admin.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class logging {

    @Before("execution(* com.example.admin..controller..*.*(..)) || execution(* com.example.admin..service..*.*(..))")
    public void logBeforeMethodCall(JoinPoint joinPoint) {
        try {
            logInfo("Execution start:",joinPoint);
            for (Object object : joinPoint.getArgs()) {
                log.debug(object.toString()); 
             }
        } catch (Exception e) {
            //Not stopping execution
            log.info("Error during converting args to String, continuing mehtod execution");
        }
    }

    @After("execution(* com.example.admin..controller..*.*(..)) || execution(* com.example.admin..service..*.*(..))")
    public void logAfterMethodCall(JoinPoint joinPoint) {
        try {
            logInfo("Execution end:",joinPoint);
        } catch (Exception e) {
            //Not stopping execution
            log.info("Error during converting args to String, continuing mehtod execution");
        }
    }

    private void logInfo(String prefix,JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.info("{} {}.{}",prefix,className,methodName);
    }
    
}
