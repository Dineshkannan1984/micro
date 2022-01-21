package com.example.admin.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class logging {

    @Around("execution(* com.example.admin..controller..*.*(..)) || execution(* com.example.admin..service..*.*(..))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        
        log.info("Execution start {}.{}",className,methodName);

        final StopWatch stopWatch = new StopWatch();
          
        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        //Log method execution time
        log.info("Execution end {}.{} Execution time: {} ms",className,methodName,stopWatch.getTotalTimeMillis());

        return result;
    }

    // @Before("execution(* com.example.admin..controller..*.*(..)) || execution(* com.example.admin..service..*.*(..))")
    // public void logBeforeMethodCall(JoinPoint joinPoint) {
    //     try {
    //         logInfo("Execution start:",joinPoint);
    //         for (Object object : joinPoint.getArgs()) {
    //             log.debug(object.toString()); 
    //          }
    //     } catch (Exception e) {
    //         //Not stopping execution
    //         log.info("Error during converting args to String, continuing mehtod execution");
    //     }
    // }

    // @After("execution(* com.example.admin..controller..*.*(..)) || execution(* com.example.admin..service..*.*(..))")
    // public void logAfterMethodCall(JoinPoint joinPoint) {
    //     try {
    //         logInfo("Execution end:",joinPoint);
    //     } catch (Exception e) {
    //         //Not stopping execution
    //         log.info("Error during converting args to String, continuing mehtod execution");
    //     }
    // }

    // private void logInfo(String prefix,JoinPoint joinPoint){
    //     String className = joinPoint.getTarget().getClass().getSimpleName();
    //     String methodName = joinPoint.getSignature().getName();
    //     log.info("{} {}.{}",prefix,className,methodName);
    // }
    
}
