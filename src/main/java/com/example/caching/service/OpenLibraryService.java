
package com.example.caching.service;

import com.example.caching.client.OpenLibraryClient;
import com.example.caching.dto.OpenLibraryBookDto;
import com.example.caching.dto.OpenLibrarySearchResponse;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OpenLibraryService {

    //constructor injection -- provides the OpenLibraryClient object

    private final OpenLibraryClient openLibraryClient;


        //  calling the client
    public OpenLibraryService(OpenLibraryClient openLibraryClient) {
        this.openLibraryClient = openLibraryClient;
    }

//declaring method --accept a book title, search Open Library, and return a list of matching books.  
  public List<OpenLibraryBookDto> searchBooks(String title) {

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                    "Book title cannot be empty"
            );
        }

        //This is where our service asks the client to contact Open Library.

        OpenLibrarySearchResponse response =
                openLibraryClient.searchBooks(title);

        if (response == null || response.getDocs() == null) {
            return Collections.emptyList();
        }


            //Extracting the books
        return response.getDocs();
    }
}
