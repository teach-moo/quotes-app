package com.teach.quotes_api.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class QuoteRepositoryIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");

    @Autowired
    private QuoteRepository quoteRepository;

    @Test
    void savesAndRetrievesQuote() {
        Quote quote = new Quote();
        quote.setText("Integration test quote");
        quote.setAuthor("Tester");

        Quote saved = quoteRepository.save(quote);

        assertThat(quoteRepository.findById(saved.getId())).isPresent();
    }
}