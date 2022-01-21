package com.example.admin.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
}
