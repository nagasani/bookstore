package com.example.bookstore.service;

import java.util.List;
import java.util.Random;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;

@Service
public class BookOfTheMomentService 
{
    private final BookRepository bookRepository;
    private Book currentBookOfTheMoment;

    public BookOfTheMomentService(BookRepository bookRepository) 
    {
        this.bookRepository = bookRepository;
        updateBookOfTheMoment(); // Initialize the currentBookOfTheMoment at startup
    }

    public Book getCurrentBookOfTheMoment() 
    {
        return currentBookOfTheMoment;
    }

    @Scheduled(fixedRate = 100000) // 2 hours = 7,200,000 milliseconds
    public void updateBookOfTheMoment() 
    {
        List<Book> allBooks = bookRepository.findAll();
        if (!allBooks.isEmpty()) 
        {
            currentBookOfTheMoment = allBooks.get(new Random().nextInt(allBooks.size()));
            System.out.println("Book of the Moment Title "+currentBookOfTheMoment.getTitle()+" ID: "+currentBookOfTheMoment.getId());
        }
    }
}