package com.example.bookstore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceAutoConfiguration {
	
	@Value("$server.port")
	private static int serverPort;
	
	static{
		System.out.println(serverPort);
	}

}
