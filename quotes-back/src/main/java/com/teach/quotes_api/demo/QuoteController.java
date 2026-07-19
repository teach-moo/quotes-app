package com.teach.quotes_api.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/quotes")

public class QuoteController {
    

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public List<Quote> getAll() {
        return quoteService.findAll();
    }

    @GetMapping("/{id}")
    public Quote getById(@PathVariable UUID id) {
        return quoteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Quote create(@Valid @RequestBody Quote quote) {
        return quoteService.create(quote);
    }

    @PutMapping("/{id}")
    public Quote update(@PathVariable UUID id, @Valid @RequestBody Quote quote) {
        return quoteService.update(id, quote);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        quoteService.delete(id);
    }
    
    
}