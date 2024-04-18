package com.example.bookstore.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

    @Scheduled(fixedRate = 7200000) // 2 hours = 7,200,000 milliseconds
    public void updateBookOfTheMoment() 
    {
        List<Book> allBooks = bookRepository.findAll();
        if (!allBooks.isEmpty()) 
        {
            currentBookOfTheMoment = allBooks.get(new Random().nextInt(allBooks.size()));
            System.out.println(LocalDateTime.now(ZoneId.of("America/New_York")).format(DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy HH:mm:ss"))+" : "+"Book of the Moment Title "+currentBookOfTheMoment.getTitle()+" ID: "+currentBookOfTheMoment.getId());
            System.out.println(LocalDateTime.now(ZoneId.of("America/Chicago")).format(DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy HH:mm:ss"))+" Chicago Time : "+"Book of the Moment Title "+currentBookOfTheMoment.getTitle()+" ID: "+currentBookOfTheMoment.getId());
        }
    }
}