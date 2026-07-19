package com.teach.quotes_api.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface QuoteRepository extends JpaRepository<Quote, UUID> {
}