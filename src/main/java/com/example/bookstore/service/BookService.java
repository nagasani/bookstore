package com.example.bookstore.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.example.bookstore.entity.Book;
import com.example.bookstore.exception.EmptyInputExcpetion;
import com.example.bookstore.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BookService {

    private final BookRepository bookRepository;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Book> findBookById(Long id) {
    	System.out.println("Rama Raju Nagasani : "+id);
        return bookRepository.findById(id);
    }

    @Transactional
    public Book addBook(Book book) 
    {    	
    	if(book.getStatus().isEmpty()) 
		{
			throw new EmptyInputExcpetion("601", "Input Field Empty");
		}
    	Book savedBook = bookRepository.save(book);
    	sendBookAsJsonAsync(savedBook);
        return savedBook;
    }
    
    @Async
    public void sendBookAsJsonAsync(Book book) 
    {    
    	try 
    	{
			Thread.sleep(2000);
		} 
    	catch (InterruptedException e) 
    	{			
			e.printStackTrace();
		}
    	String bookJson;
        try 
        {
            bookJson = objectMapper.writeValueAsString(book);
        } 
        catch (JsonProcessingException e) 
        {
            logger.error("Error converting book to JSON", e);
            return;
        }        
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("new-books", bookJson);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
            	logger.info("Sent message=[" + book.getTitle() + 
                    "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
            	logger.info("Unable to send message=[" + 
            			book.getTitle() + "] due to : " + ex.getMessage());                
            }
        });
    }

    //Just Practice Require is default
    @Transactional(propagation = Propagation.REQUIRED)
    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setPrice(updatedBook.getPrice());
                    return bookRepository.save(book);
                }).orElseThrow(() -> new RuntimeException("Book not found with id " + id));
    }

    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
    public void bulkUpdateBooksStatus(String status, List<Long> bookIds) {
        int updatedCount = bookRepository.updateBookStatusBulk(status, bookIds);
        System.out.println(updatedCount + " books were updated.");
    }
}
