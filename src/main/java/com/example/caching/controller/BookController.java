
package com.example.caching.controller;

import com.example.caching.Book;
import com.example.caching.dto.OpenLibraryBookDto;
import com.example.caching.service.BookService;
import com.example.caching.service.OpenLibraryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    //Injected OpenLibraryService
    private final OpenLibraryService openLibraryService;


    //Constructor injection
    public BookController(
            BookService bookService,
            OpenLibraryService openLibraryService) {

        this.bookService = bookService;
        this.openLibraryService = openLibraryService;
    }

    // GET - Retrieve a local book
    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable String isbn) {

        Book book = bookService.getBook(isbn);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book);
    }

    // GET - Search books from Open Library
    @GetMapping("/search")
    public ResponseEntity<List<OpenLibraryBookDto>> searchBooks(
            @RequestParam String title) {

        if (title.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        List<OpenLibraryBookDto> books =
                openLibraryService.searchBooks(title);

        return ResponseEntity.ok(books);
    }

    // POST - Add a new local book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        Book savedBook = bookService.addBook(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    // PUT - Update an existing local book
    @PutMapping("/{isbn}")
    public ResponseEntity<Book> updateBook(
            @PathVariable String isbn,
            @RequestBody Book book) {

        Book updatedBook = bookService.updateBook(isbn, book);

        if (updatedBook == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedBook);
    }

    // DELETE - Delete a local book
    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {

        bookService.deleteBook(isbn);

        return ResponseEntity.noContent().build();
    }
}
