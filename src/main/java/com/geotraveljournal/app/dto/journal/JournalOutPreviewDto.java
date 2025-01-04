package com.geotraveljournal.app.dto.journal;

import java.time.Instant;

public class JournalOutPreviewDto {
    private Long id;
    private String title;
    private Instant createdAt;

    public JournalOutPreviewDto(Long id, String title, Instant createdAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
