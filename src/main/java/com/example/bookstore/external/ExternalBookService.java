package com.example.bookstore.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalBookService {

    private final RestTemplate restTemplate;

    @Autowired
    public ExternalBookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExternalBook getBookDetails(String isbn) {
        String url = "https://example.com/api/books/" + isbn;
        try {
            ResponseEntity<ExternalBook> response = restTemplate.getForEntity(url, ExternalBook.class);           
            response.getStatusCode();
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                // Handle non-2xx responses
                throw new ExternalBookServiceException("Failed to fetch book details for ISBN: " + isbn);
            }
        } catch (RestClientException e) {
            throw new ExternalBookServiceException("Error communicating with the book information service", e);
        }
    }
}
