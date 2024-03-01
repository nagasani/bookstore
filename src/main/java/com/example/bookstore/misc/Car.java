package com.example.bookstore.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class Car 
{	
	@Autowired
	Engine engine;
	
	@PostConstruct
	public void start() {
		System.out.println("Start method called...");
	}
	
	@PreDestroy
	public void stop() {
		System.out.println("Start method called...");
	}
	
	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	public Car(Engine engine) {
		super();
		this.engine = engine;
	}

	public String getEngineData() 
	{
		return engine.name;
	}
}