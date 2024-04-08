package com.example.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaConfig {

    @Bean
    public ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate(
            ProducerFactory<String, String> producerFactory,
            ConcurrentKafkaListenerContainerFactory<String, String> containerFactory) {
        
        ConcurrentMessageListenerContainer<String, String> repliesContainer =
                containerFactory.createContainer("book-response");
        repliesContainer.getContainerProperties().setGroupId("bookResponseGroup");

        return new ReplyingKafkaTemplate<>(producerFactory, repliesContainer);
    }
}
