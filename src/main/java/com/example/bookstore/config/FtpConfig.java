package com.example.bookstore.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ftp.server")
public class FtpConfig {

    private String address;
    private int port;
    private String username;
    private String password;
    private String enabled;

    // Getters and setters

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
   	 * @return the ftpEnabled
   	 */
   	public String getEnabled() {
   		return enabled;
   	}

   	/**
   	 * @param ftpEnabled the ftpEnabled to set
   	 */
   	public void setEnabled(String enabled) {
   		this.enabled = enabled;
   	}
}
