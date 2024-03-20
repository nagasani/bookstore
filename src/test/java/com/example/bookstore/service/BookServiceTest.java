package com.example.bookstore.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookServiceTest {
	
	@InjectMocks
	BookService bookservice;
	
	@Mock
	BookRepository bookRepository;
	
	@Test
	public void findBookByIdTest() 
	{
		when(bookRepository.findById(1L)).thenReturn(createBookStub());
		
		Book testedBook = bookservice.findBookById(1L).get();
		
		assertEquals(testedBook.getTitle(), "test_title");
	}
	
	private Optional<Book> createBookStub(){		
		Book book = new Book(1L, "test_title", 12.25, new Author("Test Author"), "Available");
		return Optional.of(book);
	}
}
