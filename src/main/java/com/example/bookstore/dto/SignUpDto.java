package com.example.bookstore.dto;

import com.example.bookstore.entity.Role;

// dtos/SignUpDto.java
public record SignUpDto(
    String login,
    String password,
    Role role) 
{
}