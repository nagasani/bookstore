package com.example.bookstore.aop;

import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect 
{
	@Around("execution(* com.example.bookstore..*(..))")
	public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable 
	{
		String methodName = joinPoint.getSignature().getName();
		Object[] methodArgs = joinPoint.getArgs();
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

		logger.info("Entering method: " + methodName + " with arguments " + Arrays.toString(methodArgs));

		try 
		{
			Object result = joinPoint.proceed(); // Continue with method execution
			logger.info("Method " + methodName + " executed successfully.");
			return result;
		} 
		catch (Throwable throwable) 
		{
			logger.error("Exception in method: " + methodName, throwable);
			throw throwable; // Rethrow the exception
		} 
		finally 
		{
			logger.info("Exiting method: " + methodName);
		}
	}
}
