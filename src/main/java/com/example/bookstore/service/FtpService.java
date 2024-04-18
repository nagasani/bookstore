package com.example.bookstore.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FtpService {

    private String serverAddress;
    private int port;
    private String username;
    private String password;
    private String enabled;

    private FTPClient ftpClient = new FTPClient();
    
    @Autowired
    private EmailService emailService; // Inject the EmailService

    public FtpService(@Value("${ftp.server.address}") String serverAddress,
                      @Value("${ftp.server.port}") int port,
                      @Value("${ftp.server.username}") String username,
                      @Value("${ftp.server.password}") String password, 
                      @Value("${ftp.server.enabled}") String enabled) {
        this.serverAddress = serverAddress;
        this.port = port;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        try {
            connectToServer();
        } catch (IOException e) {
            System.out.println("Error connecting to FTP server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void connectToServer() throws IOException {
        if ("true".equals(enabled)) {
            ftpClient.connect(serverAddress, port);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));
            System.out.println("Connected to FTP server at " + serverAddress + ":" + port);
        }
    }

    public void uploadFile(InputStream InputStream, String remoteFileName) {
        try (InputStream) {
            ftpClient.storeFile(remoteFileName, InputStream);
            System.out.println("Uploaded file as: " + remoteFileName);
            // Example sending an email after successful upload
            emailService.sendSimpleMessage("user@example.com", "Upload Success", "The file has been uploaded successfully!");
        } catch (IOException e) {
            System.out.println("Error uploading file: " + e.getMessage());
        }
    }

    @Scheduled(fixedRate = 7200000) // 2 hours = 7,200,000 milliseconds
    public void uploadBatchFile() 
    {
        try (FileInputStream fileInputStream = new FileInputStream("C:/Users/nagas/Desktop/ftp/BooksData.csv")) 
        {
            String filePath = "BooksData.csv";
            ftpClient.storeFile(filePath, fileInputStream);
            System.out.println("Uploaded file as: " + filePath);
            // Notify via email about batch upload
            emailService.sendSimpleMessage("naga@gmail.com", "Batch Upload Success", "Batch file uploaded successfully!");
        } 
        catch (Exception e) 
        {
            System.out.println("Error uploading batch file: " + e.getMessage());
            // Optionally notify about failure via email
            emailService.sendSimpleMessage("naga_user@gmail.com", "Batch Upload Failure", "Failed to upload batch file: " + e.getMessage());
        }
    }
}
