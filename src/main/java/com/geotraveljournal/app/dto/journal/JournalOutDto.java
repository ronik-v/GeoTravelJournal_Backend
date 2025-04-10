package com.geotraveljournal.app.dto.journal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;
import java.util.List;

public class JournalOutDto {
    private Long id;
    private String title;
    private String description;
    private Double distance;
    private List<Object> route;
    @JsonIgnore
    private Long userId;
    private Instant createdAt;
    private Instant updatedAt;

    public JournalOutDto() {
    }

    public JournalOutDto(Object[] data) {
        this.id = data[0] != null ? ((Number) data[0]).longValue() : null;
        this.title = data[1] != null ? data[1].toString() : null;
        this.createdAt = data[2] != null ? Instant.parse(data[2].toString()) : null;
    }

    public JournalOutDto(Long id, String title, String description, Double distance, List<Object> route, Long userId, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.distance = distance;
        this.route = route;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public List<Object> getRoute() {
        return route;
    }

    public void setRoute(List<Object> route) {
        this.route = route;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
