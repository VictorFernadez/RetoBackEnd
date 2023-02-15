package com.victor.starter.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class TimeLoggingAspect {

    private final Logger log;

    public TimeLoggingAspect(String loggerName) {
        log = LoggerFactory.getLogger(loggerName);
    }
    @Around("execution(* com.victor.fernandez.client.service.*.*(*))")
    public Object logMethodTiming(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        Object returnValue = proceedingJoinPoint.proceed();

        long totalTime = System.currentTimeMillis() - startTime;

        log.info("Total de tiempo de ejecución del request '{}' is {} ms", proceedingJoinPoint.getSignature().getName(),
                totalTime);

        return returnValue;
    }
}
