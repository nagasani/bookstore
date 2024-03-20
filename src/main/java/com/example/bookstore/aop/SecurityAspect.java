package com.example.bookstore.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Aspect
public class SecurityAspect {

    @Before("execution(* com.example.bookstore.service.PaymentService.processPayment(..))")
    public void checkAuthenticationBeforePayment(JoinPoint joinPoint) 
    {
        // Assume a SecurityContextHolder provides the current user's authentication details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) 
        {
            throw new SecurityException("Authentication required for processing payment.");
        }
        // Additional checks can be performed here, such as verifying user roles
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logger.info("Authentication verified for user: " + authentication.getName());
    }
}
