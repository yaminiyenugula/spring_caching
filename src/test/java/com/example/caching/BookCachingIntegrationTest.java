package com.example.caching;

import com.example.caching.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookCachingIntegrationTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private CacheManager cacheManager;

    private Cache bookCache;

    @BeforeEach
    void setUp() {
        bookCache = cacheManager.getCache("books");
        assertNotNull(bookCache);
        bookCache.clear();
    }

    @Test
    void shouldCacheBookAfterFirstRequest() {
        Book book = bookService.getBook("isbn-1234");

        assertNotNull(book);
        assertEquals("Spring Boot in Action", book.title());

        Book cachedBook = bookCache.get("isbn-1234", Book.class);

        assertNotNull(cachedBook);
        assertEquals(book, cachedBook);
    }

    @Test
    void shouldUpdateCacheWhenBookIsUpdated() {
        Book updatedBook = new Book(
                "isbn-1234", "Advanced Spring Boot");

        Book result = bookService.updateBook(
                "isbn-1234", updatedBook);

        assertNotNull(result);
        assertEquals("Advanced Spring Boot", result.title());

        Book cachedBook = bookCache.get("isbn-1234", Book.class);

        assertNotNull(cachedBook);
        assertEquals("Advanced Spring Boot", cachedBook.title());
    }

    @Test
    void shouldRemoveBookFromCacheWhenDeleted() {
        bookService.getBook("isbn-1234");

        assertNotNull(bookCache.get("isbn-1234"));

        bookService.deleteBook("isbn-1234");

        assertNull(bookCache.get("isbn-1234"));
    }
}