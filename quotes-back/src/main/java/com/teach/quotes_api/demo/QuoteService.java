package com.teach.quotes_api.demo;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public List<Quote> findAll() {
        return quoteRepository.findAll();
    }

    public Quote findById(UUID id) {
        return quoteRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException(id));
    }

    public Quote create(Quote quote) {
        return quoteRepository.save(quote);
    }

    public Quote update(UUID id, Quote updated) {
        Quote existing = findById(id);
        existing.setText(updated.getText());
        existing.setAuthor(updated.getAuthor());
        return quoteRepository.save(existing);
    }

    public void delete(UUID id) {
        Quote existing = findById(id);
        quoteRepository.delete(existing);
    }
}