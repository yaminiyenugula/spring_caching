package com.example.caching.repository;

import com.example.caching.Book;

public interface BookRepository {

    Book getByIsbn(String isbn);

    Book save(Book book);

    Book update(String isbn, Book book);

    void delete(String isbn);
}