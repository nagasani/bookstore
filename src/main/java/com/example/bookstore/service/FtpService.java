package com.example.bookstore.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FtpService {

    private String serverAddress;
    private int port;
    private String username;
    private String password;
    private String enabled;
    
    private FTPClient ftpClient = new FTPClient();

    public FtpService(@Value("${ftp.server.address}") String serverAddress,
                      @Value("${ftp.server.port}") int port,
                      @Value("${ftp.server.username}") String username,
                      @Value("${ftp.server.password}") String password, 
                      @Value("${ftp.enabled") String enabled
                      ) 
    {
        this.serverAddress = serverAddress;
        this.port = port;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        try 
        {        	
        	connectToServer();
        } 
        catch (IOException e) 
        {
            System.out.println("Error connecting to FTP server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void connectToServer() throws IOException 
    {
    	if ("true".equals(enabled)) 
    	{
	        ftpClient.connect(serverAddress, port);
	        ftpClient.login(username, password);
	        ftpClient.enterLocalPassiveMode();
	        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	        System.out.println("Connected to FTP server at " + serverAddress + ":" + port);
    	}
    }

    public void uploadFile(InputStream InputStream, String remoteFileName) 
    {
        try
        {
            ftpClient.storeFile(remoteFileName, InputStream);
            System.out.println("Uploaded file as: " + remoteFileName);
        } 
        catch (IOException e) 
        {
            System.out.println("Error uploading file: " + e.getMessage());
        }
    }

    public void downloadFile(String remoteFilePath, String localFilePath) {
        try (FileOutputStream fos = new FileOutputStream(localFilePath)) {
            boolean done = ftpClient.retrieveFile(remoteFilePath, fos);
            if (done) {
                System.out.println("File downloaded successfully: " + remoteFilePath);
            } else {
                System.out.println("File download failed: " + remoteFilePath);
            }
        } catch (IOException e) {
            System.out.println("Error downloading file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
