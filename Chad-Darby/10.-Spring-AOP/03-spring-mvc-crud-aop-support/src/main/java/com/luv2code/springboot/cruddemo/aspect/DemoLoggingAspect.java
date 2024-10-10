package com.luv2code.springboot.cruddemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {
    // Setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.luv2code.springboot.cruddemo.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.luv2code.springboot.cruddemo.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* com.luv2code.springboot.cruddemo.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {}

    @Before("forAppFlow()")
    public void beforeApp(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("====> @BeforeAdvice method name: " + method);

        // Display and get the arguments
        Object[] args = joinPoint.getArgs();

        // Loop through on arguments
        for (Object tempArgs : args) {
            myLogger.info("=====> arguments: " + tempArgs);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"
    )
    public void afterApp(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("=====>  @AfterReturning method name: " + method);

        myLogger.info("=====> result: " + result);
    }
}
