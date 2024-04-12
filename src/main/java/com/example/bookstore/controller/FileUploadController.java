package com.example.bookstore.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.bookstore.service.FtpService;

@RestController
public class FileUploadController {

    private final FtpService ftpService;

    @Autowired
    public FileUploadController(FtpService ftpService) {
        this.ftpService = ftpService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            // Save the file temporarily in the local file system
            //Path tempFile = Files.createTempFile("upload-", file.getOriginalFilename());
            //Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

            // Upload the file to the FTP server
            //ftpService.uploadFile(tempFile.toString());

            // Optionally, delete the temporary file after upload
            //Files.delete(tempFile);
        	String originalFileName = Long.toString(System.currentTimeMillis())+"_"+file.getOriginalFilename();
            ftpService.uploadFile(file.getInputStream(), originalFileName);            

            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Could not upload the file: " + file.getOriginalFilename());
        }
    }
}
