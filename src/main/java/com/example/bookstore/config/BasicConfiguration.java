package com.example.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("user")
            .password(passwordEncoder.encode("password"))
            .roles("USER")
            .build();

        UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder.encode("admin"))
            .roles("USER", "ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request -> request.anyRequest()
                .authenticated())
            .httpBasic(Customizer.withDefaults())
            .build();
    }
    */
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http.headers(headers -> headers.frameOptions(frameOption -> frameOption.sameOrigin()));
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.GET, "/users","/user","/user/**",
                                "/h2-console/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/users","/user","/user/**",
                                "/h2-console/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.PUT, "/users","/user","/user/**",
                                "/h2-console/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/books","/books","api/books**",
                                "/h2-console/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/books","/books","api/books/**",
                                "/h2-console/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.PUT, "/books","/books","api/books/**",
                                "/h2-console/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/users","/user","/user/**",
                                "/h2-console/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/upload","/upload/**",
                                "/h2-console/**")
                        .permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
    //http://localhost:8081/api/books/6
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }
}