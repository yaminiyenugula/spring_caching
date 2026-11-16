
package com.example.caching;

import org.springframework.stereotype.Component;
import org.springframework.cache.annotation.Cacheable;

@Component
public class SimpleBookRepository implements BookRepository {

    @Override
    @Cacheable("books")
    public Book getByIsbn(String isbn) {

        simulateSlowService();

        return new Book(isbn, "Some book");
    }

    private void simulateSlowService() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }
}
