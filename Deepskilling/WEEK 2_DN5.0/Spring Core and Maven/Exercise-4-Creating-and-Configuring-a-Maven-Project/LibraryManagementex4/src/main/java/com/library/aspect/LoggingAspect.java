package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

    @Before("execution(* com.library.management.*.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {

        System.out.println("----------------------------------------");
        System.out.println("AOP BEFORE ADVICE");
        System.out.println("Executing Method : " + joinPoint.getSignature().getName());
        System.out.println("----------------------------------------");
    }

    @After("execution(* com.library.management.*.*(..))")
    public void afterMethod(JoinPoint joinPoint) {

        System.out.println("----------------------------------------");
        System.out.println("AOP AFTER ADVICE");
        System.out.println("Completed Method : " + joinPoint.getSignature().getName());
        System.out.println("----------------------------------------");
    }
}