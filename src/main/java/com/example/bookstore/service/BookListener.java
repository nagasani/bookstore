package com.example.bookstore.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookListener {

    @KafkaListener(topics = "new-books", groupId = "bookstore-group")
    public void listen(String bookTitle) 
    {
        System.out.println("New book added to the bookstore: " + bookTitle);
    }
}
