package com.example.bookstore.external;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.config.RequestConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        // Configure timeouts
        RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(5000) // Set connection timeout: 5000 ms
            .setSocketTimeout(5000) // Set read timeout: 5000 ms
            .build();
        
        // Create an HttpClient with the RequestConfig
        HttpClient client = HttpClientBuilder.create()
            .setDefaultRequestConfig(config)
            .build();
        
        // Use the HttpComponentsClientHttpRequestFactory with our custom HttpClient
//new HttpComponentsClientHttpRequestFactory(null)
      //Will check later
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }
}
