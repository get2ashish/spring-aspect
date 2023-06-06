package github.com.get2ashish.springaspect.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ControllerAspect {

    //Aspect in Spring is a class that addresses your cross-cutting concerns.

    //Advice --> Before, After, Around
    //It’s the behavior that addresses system-wide concerns (logging, security checks, etc).
    //This behavior is represented by a method to be executed at a JoinPoint. This behavior can be executed
    //Before, After, or Around the JoinPoint according to the Advice type.


    //Pointcut expression
    //1 initial star * is for return type of the method we say it can be any
    //2 then we specify the fully qualified name of the class.* means any method of that class for any argument (..)
    //A Pointcut is an expression that defines at what JoinPoints a given Advice should be applied.


    //JoinPoint is the place (method) where your business logic is implemented
    //JoinPoint is a point in the execution flow of a method where an Aspect can be plugged in.

    @Before(value = "execution(* github.com.get2ashish.springaspect.controller.EmployeeController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.info("Controller class {} Method {} about to be executed with arguments {}", getTargetClassName(joinPoint), joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @After(value = "execution(* github.com.get2ashish.springaspect.controller.EmployeeController.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        log.info("Controller class {} Method {} executed with arguments {}", getTargetClassName(joinPoint), joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    private String getTargetClassName(JoinPoint joinPoint) {
        try {
            return joinPoint.getTarget().getClass().getSimpleName();
        } catch (Exception e) {
            log.error("Unable to obtain the target class name, default name will be used!");
        }
        return "DefaultController";
    }

}
