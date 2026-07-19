package com.teach.quotes_api.demo;

import java.util.UUID;

public class QuoteNotFoundException extends RuntimeException {
    public QuoteNotFoundException(UUID id) {
        super("Quote not found: " + id);
    }
    public QuoteNotFoundException(String message) {
        super(message);
    }    
}