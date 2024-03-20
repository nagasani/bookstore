package com.example.bookstore.external;

public class ExternalBook {
	
	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private Integer yearPublished;

	// Default constructor
	public ExternalBook() {
	}

	// Constructor with all fields
	public ExternalBook(String isbn, String title, String author, String publisher, Integer yearPublished) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.yearPublished = yearPublished;
	}

	// Getters and Setters
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(Integer yearPublished) {
		this.yearPublished = yearPublished;
	}

	// toString() method to represent the object as a string
	@Override
	public String toString() {
		return "ExternalBook{" + "isbn='" + isbn + '\'' + ", title='" + title + '\'' + ", author='" + author + '\''
				+ ", publisher='" + publisher + '\'' + ", yearPublished=" + yearPublished + '}';
	}
}
