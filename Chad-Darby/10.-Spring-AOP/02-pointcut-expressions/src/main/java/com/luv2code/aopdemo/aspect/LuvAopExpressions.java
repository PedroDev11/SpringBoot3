package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LuvAopExpressions {
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {}

    // Create pointcut for getter/setter methods
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
    public void getterMethods() {}

    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
    public void setterMethods() {}

    // Create pointcut for combine pointcut declarations and exclude getter/setter methods
    @Pointcut("forDaoPackage() && !(getterMethods() || setterMethods())")
    public void forDaoPackageNoGetterSetter() {}
}
