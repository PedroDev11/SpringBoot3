package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class DemoLoggingAspect {
    // @Around runs before and after the advice or the method, now, ProceedingJoinPoint that's just
    // a handle to the actual target method
    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
        // Print out method we're advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n======> Executing @AroundAdvice on method: " + method);

        long begin = System.currentTimeMillis();

        // execute the method
        Object result = null;

        // handle exception to send a default value and not the exception.
        try {
            result = theProceedingJoinPoint.proceed();
        } catch (Exception exc) {
            // Default response to not send the exception
            // result = "Major accident! But no worries, your private AOP helicopter is on the way";

            // rethrow exception
            return exc;
        }

        long end = System.currentTimeMillis();

        // Compute duration and display it
        long duration = end - begin;
        System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

    // @After will run for success or failure (like a finally block)
    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n======> Executing @AfterFinally on method: " + method);
    }

    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n======> Executing @AfterThrowing on method: " + method);

        System.out.println("\n======> The exception is: " + theExc);
    }

    // Add new advice for @AfterReturning on the findAccounts() method
    // result is the variable that we want to use where AOP will inject the results for our application
    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccounts(JoinPoint theJoinPoint, List<Account> result) {
        // Print out witch method we're adviding on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n======> Executing @AfterReturning on method: " + method);

        // Print out the results of the method call
        System.out.println("\n======> result: " + result);

        // Let's post-process the data, modify it
        convertAccountNamesToUpperCase(result);
        System.out.println("\n======> result: " + result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        for (Account tempAccount : result) {
            String theUpperName = tempAccount.getName().toUpperCase();
            tempAccount.setName(theUpperName);
        }
    }

    // JoinPoint gives us metada about the actual method that's executing at that given time
    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("\n====> Executing @Before advice on method");

        // Display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("method: " + methodSignature);

        // Display method arguments -> an array of arguments
        Object[] args = joinPoint.getArgs();

        for (Object tempArgs : args) {
            System.out.println("Argument: " + tempArgs);

            // if It's an instance of Account, I downcast it accordingly and I print out the account
            // specific data, getName(), getLevel()
            if (tempArgs instanceof Account) {
                // Downcast and print Account specific stuff
                // then I want to kind of downcast it and get some actual data from that account,
                // because by default, It's gonna simply print out the account objects hash code of the
                // default toString() and we didn't provide a special toString() here, so we'll need to
                // kind of pull it out individually
                Account theAccount = (Account) tempArgs;
                System.out.println("account name: " + theAccount.getName());
                System.out.println("account level: " + theAccount.getLevel());
            }
        }
    }
}
