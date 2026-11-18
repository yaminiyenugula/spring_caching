
package com.example.caching;

import com.example.caching.repository.BookRepository;
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


    //// Spring automatically pulls the RestTemplate bean from its storage room and plugs it in here!
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CacheManager cacheManager;

    private Cache bookCache;

    private static final String TEST_ISBN = "test-isbn-1234";

    @BeforeEach
    void setUp() {
        bookCache = cacheManager.getCache("books");

        assertNotNull(bookCache);

        // Clear the cache before each test
        bookCache.clear();

        // Prepare a known book in SQLite
        bookRepository.save(
            new Book(TEST_ISBN, "Spring Boot in Action")
        );
    }

    @Test
    void shouldCacheBookAfterFirstRequest() {

        Book book = bookService.getBook(TEST_ISBN);

        assertNotNull(book);
        assertEquals("Spring Boot in Action", book.getTitle());

        Book cachedBook = bookCache.get(TEST_ISBN, Book.class);

        assertNotNull(cachedBook);
        assertEquals(book.getTitle(), cachedBook.getTitle());
    }

    @Test
    void shouldUpdateCacheWhenBookIsUpdated() {

        Book updatedBook = new Book(
            TEST_ISBN,
            "Advanced Spring Boot"
        );

        Book result = bookService.updateBook(
            TEST_ISBN,
            updatedBook
        );

        assertNotNull(result);
        assertEquals("Advanced Spring Boot", result.getTitle());

        Book cachedBook = bookCache.get(TEST_ISBN, Book.class);

        assertNotNull(cachedBook);
        assertEquals("Advanced Spring Boot", cachedBook.getTitle());
    }

    @Test
    void shouldRemoveBookFromCacheWhenDeleted() {

        bookService.getBook(TEST_ISBN);

        assertNotNull(bookCache.get(TEST_ISBN));

        bookService.deleteBook(TEST_ISBN);

        assertNull(bookCache.get(TEST_ISBN));
    }
}
