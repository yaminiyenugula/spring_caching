
package com.example.caching.service;

import com.example.caching.Book;
import com.example.caching.repository.BookRepository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Retrieve a book
    //storing cache memory
    @Cacheable(value = "books", key = "#isbn")
    public Book getBook(String isbn) {

        System.out.println("Fetching book from SQLite database...");
        //The Optional<Book> is used because the database might not contain the requested ISBN.
        Optional<Book> book = bookRepository.findById(isbn);

        return book.orElse(null);
    }

    // Add a new book
    @CachePut(value = "books", key = "#result.isbn")
    public Book addBook(Book book) {

        System.out.println("Saving book to SQLite database...");

        return bookRepository.save(book);
    }

    // Update an existing book
    @CachePut(value = "books", key = "#isbn")
    public Book updateBook(String isbn, Book book) {

        System.out.println("Updating book in SQLite database...");

        Optional<Book> existingBook = bookRepository.findById(isbn);

        if (existingBook.isEmpty()) {
            return null;
        }

        Book updatedBook = existingBook.get();
        updatedBook.setTitle(book.getTitle());

        return bookRepository.save(updatedBook);
    }

    // Delete a book
    @CacheEvict(value = "books", key = "#isbn")
    public void deleteBook(String isbn) {

        System.out.println("Deleting book from SQLite database...");

        bookRepository.deleteById(isbn);
    }
}
