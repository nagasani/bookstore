package com.example.bookstore.advice;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.bookstore.exception.EmptyInputExcpetion;

@ControllerAdvice
public class MyControllerAdvice {

	@ExceptionHandler(EmptyInputExcpetion.class)
	public ResponseEntity<String> handleEmptyInput(EmptyInputExcpetion emptyInputExcpetion)
	{
		return new ResponseEntity<String>("Input field is Empty", HttpStatus.BAD_REQUEST);		
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException)
	{
		return new ResponseEntity<String>("No Value is Present in DB", HttpStatus.NOT_FOUND);		
	} 
}
