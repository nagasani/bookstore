package com.example.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig 
{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF protection as it's typically not needed for API based authentication
            .csrf(csrf -> csrf.disable())

            // Authorize requests configuration
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/login").permitAll()  // Allow public access to the login endpoint
                .anyRequest().authenticated()  // All other requests require authentication
            )

            // Disable form login and HTTP Basic authentication
            .httpBasic(httpBasic -> httpBasic.disable())
            .formLogin(form -> form.disable())
            
            // Optionally add JWT filter here if using custom filter for JWT

            // Session management to be stateless to support RESTful
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
