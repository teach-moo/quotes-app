package com.teach.quotes_api.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuoteServiceTest {

    @Mock
    private QuoteRepository quoteRepository;

    @InjectMocks
    private QuoteService quoteService;

    @Test
    void findById_returnsQuote_whenExists() {
        UUID id = UUID.randomUUID();
        Quote quote = new Quote();
        quote.setId(id);
        when(quoteRepository.findById(id)).thenReturn(Optional.of(quote));

        Quote result = quoteService.findById(id);

        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    void findById_throws_whenMissing() {
        UUID id = UUID.randomUUID();
        when(quoteRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> quoteService.findById(id))
                .isInstanceOf(QuoteNotFoundException.class);
    }

    @Test
    void findRandom_throws_whenTableEmpty() {
        when(quoteRepository.findAll()).thenReturn(List.of());

        assertThatThrownBy(() -> quoteService.findRandom())
                .isInstanceOf(QuoteNotFoundException.class);
    }

    @Test
    void update_overwritesTextAndAuthor_keepsId() {
        UUID id = UUID.randomUUID();
        Quote existing = new Quote();
        existing.setId(id);
        existing.setText("old");
        existing.setAuthor("old author");
        when(quoteRepository.findById(id)).thenReturn(Optional.of(existing));
        when(quoteRepository.save(any(Quote.class))).thenAnswer(inv -> inv.getArgument(0));

        Quote updated = new Quote();
        updated.setText("new");
        updated.setAuthor("new author");

        Quote result = quoteService.update(id, updated);

        assertThat(result.getText()).isEqualTo("new");
        assertThat(result.getAuthor()).isEqualTo("new author");
        assertThat(result.getId()).isEqualTo(id);
    }
}