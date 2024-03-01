package com.example.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.example.bookstore.config.DataSourceAutoConfiguration;
import com.example.bookstore.misc.Car;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class BookstoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(BookstoreApplication.class, args);
		Car car = context.getBean(Car.class);
		System.out.println(car.getEngineData());	
		//context.registerShutdownHook();
	}

}
