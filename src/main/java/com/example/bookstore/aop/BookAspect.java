package com.example.bookstore.aop;

import java.util.Date;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import com.example.bookstore.entity.Book;

@Aspect
@Component
public class BookAspect {

	@Before(value="execution(* com.example.bookstore.controller.BookController.*(..)) && !execution(* com.example.bookstore.controller.BookController.getBookOfTheMoment(..))")
	public void beforeAdvice(JoinPoint jointPoint) 
	{
		System.out.println("Request to "+jointPoint.getSignature()+ " Started at "+new Date());
	}
	
	@After(value="execution(* com.example.bookstore.controller.BookController.*(..)) && !execution(* com.example.bookstore.controller.BookController.getBookOfTheMoment(..))")
	public void AfterAdvice(JoinPoint jointPoint) 
	{
		System.out.println("Request to "+jointPoint.getSignature()+ " Ended at "+new Date());
	}
	
	@AfterReturning(value="execution(* com.example.bookstore.controller.BookController.getBookById(..))", returning = "book")
	public void afterAdviceReturn(JoinPoint jointPoint, Book book) 
	{
		System.out.println("The returned book ID is :"+book.getId());
	}
	
	@AfterThrowing(value="execution(* com.example.bookstore.controller.BookController.getBookById(..))", throwing="ex")
	public void afterAdviceThrow(JoinPoint jointPoint, Exception ex) 
	{
		System.out.println("The returned book ID is :"+ex.getMessage());
	}
	
	@Around(value="execution(* com.example.bookstore.controller.BookController.getBookByIdForAspect(..))")
	public void aroundAdvice(ProceedingJoinPoint jointPoint) 
	{
		System.out.println("Around started "+new Date());		
		try 
		{
			//jointPoint.proceed();
			Book book = (Book) jointPoint.proceed();
		}
		catch (Throwable e) {
			// TODO Auto-generated catch block
			System.out.println("Still Works");
			e.printStackTrace();
		}
		System.out.println("Around Ended "+new Date());
	}
	
}
