package com.example.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication()
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
//@EnableEurekaClient
//@EnableDiscoveryClient
public class BookstoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(BookstoreApplication.class, args);
		//Car car = context.getBean(Car.class);
		//System.out.println(car.getEngineData());	
		//context.registerShutdownHook();
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
