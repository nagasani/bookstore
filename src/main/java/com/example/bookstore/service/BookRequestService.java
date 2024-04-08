package com.example.bookstore.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class BookRequestService {

    @Autowired
    private ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate;

    public String requestBookDetails(String bookId) throws Exception {
        String requestTopic = "book-request";

        // Send a request and wait for the response
        RequestReplyFuture<String, String, String> sendAndReceive = replyingKafkaTemplate.sendAndReceive(new ProducerRecord<>(requestTopic, bookId));
        
        // Get the response
        SendResult<String, String> sendResult = sendAndReceive.getSendFuture().get();
        System.out.println("Message sent successfully to topic " + sendResult.getRecordMetadata().topic());
        ConsumerRecord<String, String> response = sendAndReceive.get();

        return response.value();
    }
}
