package com.example.caching.service;

import com.example.caching.Book;
import com.example.caching.repository.BookRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Cacheable(value = "books", key = "#isbn", unless = "#result == null")
    public Book getBook(String isbn) {
        System.out.println("Fetching book from repository...");
        return bookRepository.getByIsbn(isbn);
    }

    @CachePut(value = "books", key = "#result.isbn")
    public Book addBook(Book book) {
        System.out.println("Adding book...");
        return bookRepository.save(book);
    }

    @CachePut(value = "books", key = "#p0")
    public Book updateBook(String isbn, Book book) {
        System.out.println("Updating book...");
        return bookRepository.update(isbn, book);
    }

    @CacheEvict(value = "books", key = "#isbn")
    public void deleteBook(String isbn) {
        System.out.println("Deleting book...");
        bookRepository.delete(isbn);
    }
}