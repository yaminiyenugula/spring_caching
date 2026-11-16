
package com.example.caching.repository;

import com.example.caching.Book;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class SimpleBookRepository implements BookRepository {

    private final ConcurrentMap<String, Book> books =
            new ConcurrentHashMap<>();

    public SimpleBookRepository() {
        books.put("isbn-1234",
                new Book("isbn-1234", "Spring Boot in Action"));

        books.put("isbn-4567",
                new Book("isbn-4567", "Java Programming"));
    }

    @Override
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return books.get(isbn);
    }

    @Override
    public Book save(Book book) {
        books.put(book.isbn(), book);
        return book;
    }

    @Override
    public Book update(String isbn, Book book) {
        Book updatedBook = new Book(isbn, book.title());

        books.put(isbn, updatedBook);

        return updatedBook;
    }

    @Override
    public void delete(String isbn) {
        books.remove(isbn);
    }

    private void simulateSlowService() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(
                    "Book retrieval was interrupted", exception);
        }
    }
}
