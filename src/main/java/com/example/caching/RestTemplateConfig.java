
package com.example.caching;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


//Tells Spring that this class contains configuration settings.

@Configuration

public class RestTemplateConfig {

//Tells Spring to create and manage the returned object.
//Instead of creating a new RestTemplate object manually every time, Spring creates one and manages it for us.
    @Bean
    public RestTemplate restTemplate() {
        //Creates a RestTemplate object that we can use throughout our application.
        return new RestTemplate();
    }
}
