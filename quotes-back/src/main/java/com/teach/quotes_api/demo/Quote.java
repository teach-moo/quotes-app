package com.teach.quotes_api.demo;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Quote {

    @Id
    @GeneratedValue
    private UUID id;
    @NotBlank(message = "text is required")
    @Column(nullable = false, length = 500)
    private String text;

    private String author;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = Instant.now();
    }

    // getters and setters for id, text, author, createdAt, updatedAt
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }

}