
package com.example.caching.client;

import com.example.caching.RestTemplateConfig;
import com.example.caching.dto.OpenLibrarySearchResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component  // used to create object automatically
public class OpenLibraryClient {



    //Remember the RestTemplateConfig.java file we created?

    //It provides a RestTemplate bean.

    //Spring automatically injects that bean into this constructor.

    //This technique is called Dependency Injection.
    private final RestTemplate restTemplate;

    public OpenLibraryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OpenLibrarySearchResponse searchBooks(String title) {


            //Constructing the URL
        URI url = UriComponentsBuilder
                .fromUriString("https://openlibrary.org/search.json")
                .queryParam("title", title)
                .queryParam("fields",
                        "key,title,author_name,first_publish_year,cover_i")
                .queryParam("limit", 10)
                .build()
                .encode()
                .toUri();

        return restTemplate.getForObject(
                url,
                OpenLibrarySearchResponse.class
        );
    }
}
