package com.example.caching;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private final BookRepository bookRepository;

    public AppRunner(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {

        System.out.println("Fetching books");

        long start = System.currentTimeMillis();

        System.out.println(
            bookRepository.getByIsbn("isbn-1234")
        );

        System.out.println(
            bookRepository.getByIsbn("isbn-4567")
        );

        System.out.println(
            bookRepository.getByIsbn("isbn-1234")
        );

        long end = System.currentTimeMillis();

        System.out.println("Total time: " + (end - start) + " ms");
    }
}