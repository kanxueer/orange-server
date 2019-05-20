package com.skbaby.orange.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {

    @Pointcut("@annotation(com.skbaby.orange.aspect.SecurityAspect)")
    public void addAdvice(){}

    @Before("addAdvice()")
    public Object Interceptor(ProceedingJoinPoint pjp){

    }
}