package com.shaazali.thymeleafmvccrud.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    // setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* com.shaazali.thymeleafmvccrud.controller.*.*(..))")
    private void forControllerPackage(){}

    // do the same for service and dao
    @Pointcut("execution(* com.shaazali.thymeleafmvccrud.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.shaazali.thymeleafmvccrud.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {}

    // add @Before advice

    @Before(("forAppFlow()"))
    public void before(JoinPoint theJointPoint){

        // display method we are calling
        String theMethod = theJointPoint.getSignature().toShortString();
        myLogger.info("====> in @Before: calling method: " + theMethod);


        // display the arguments to the method

        // get the arguments
        Object[] args = theJointPoint.getArgs();

        // loop thru and display args
        for(Object tempArg: args){
            myLogger.info("======> argument: " + tempArg);
        }

    }

    // add @AfterReturning advice
    @AfterReturning(pointcut = "forAppFlow()",
    returning = "theResult")
    public void afterReturning(JoinPoint theJointPoint, Object theResult){

        // display method we are returning from
        String theMethod = theJointPoint.getSignature().toShortString();
        myLogger.info("====> in @Before: calling method: " + theMethod);

        // display data returned
        myLogger.info("========> result: " + theResult);
    }


}
