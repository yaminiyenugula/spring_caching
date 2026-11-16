package com.example.caching;

import com.example.caching.CachingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Verifies that the Spring Boot application starts successfully.
 */
@SpringBootTest(classes = CachingApplication.class)
class DemoApplicationTests {

    @Test
    void contextLoads() {
        // Test passes if the Spring application context loads successfully.
    }
}