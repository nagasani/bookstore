package com.example.bookstore.service;

import com.example.bookstore.entity.Book;
import com.example.bookstore.exception.EmptyInputExcpetion;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @Autowired
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
    	kafkaTemplate.send("new-books", book.getTitle());
    	
    	if(book.getStatus().isEmpty()) 
		{
			throw new EmptyInputExcpetion("601", "Input Field Empty");
		}
        return bookRepository.save(book);
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
