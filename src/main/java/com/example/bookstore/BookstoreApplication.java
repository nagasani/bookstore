package com.example.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.bookstore.misc.Car;

@SpringBootApplication()
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
public class BookstoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(BookstoreApplication.class, args);
		Car car = context.getBean(Car.class);
		System.out.println(car.getEngineData());	
		//context.registerShutdownHook();
	}

}
