package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoLoggingAspect {
    // This is where we add all of our related advices for logging
    // "execution(public void addAccount())" is a pointcut expression where don't specify a class name
    // it'll basically match on any class
    // Pointcut expression. "modifier" -> public, "return type" -> void, "Declaring type" -> give the fully
    // qualified class name of the delcaring type, "method" -> match on the addAccount()
    // "add*()" match methods starting with add in any class

    // @Before("execution(public void addAccount())")
    // @Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
    // @Before("execution(public void add*())")
    // @Before("execution(String add*())")

    // Match on params -> Class/Object, .. (Match on any number of arguments)
    // @Before("execution(* add*(com.luv2code.aopdemo.Account))")
    @Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    public void beforeAddAccountAdvice() {
        System.out.println("\n====> Executing @Before advice on method");
    }
}
