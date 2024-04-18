package com.example.bookstore.dto;

public class AuthenticationResponse {
    private String jwtToken;
    private String username; // Optional, depending on your requirements

    // Constructors
    public AuthenticationResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public AuthenticationResponse(String jwtToken, String username) {
        this.jwtToken = jwtToken;
        this.username = username;
    }

    // Getter and Setter for jwtToken
    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    // Getter and Setter for username (if needed)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
