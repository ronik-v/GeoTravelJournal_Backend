package com.geotraveljournal.app.model.journal;

import com.geotraveljournal.app.utils.JsonMapConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Map;

@Entity
@Table(name = "journal")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", nullable = true, length = 512)
    private String description;

    @NotNull
    @Column(name = "distance", nullable = false)
    private Double distance;

    @NotNull
    @Convert(converter = JsonMapConverter.class)
    @Column(name = "route", nullable = false, columnDefinition = "jsonb")
    private Map<String, Object> route;

    @Column(name = "userid", nullable = false)
    private Long userId;

    @NotNull
    @Column(name = "createdat", nullable = false, updatable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "updatedat")
    private Instant updatedAt;

    public Journal(String title, String description, Double distance,  Map<String, Object> route, Long userId) {
        this.title = title;
        this.description = description;
        this.distance = distance;
        this.route = route;
        this.userId = userId;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public Journal(String title, Double distance,  Map<String, Object> route, Long userId) {
        this.title = title;
        this.distance = distance;
        this.route = route;
        this.userId = userId;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public Journal() {}

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

    public Map<String, Object> getRoute() {
        return route;
    }

    public void setRoute(Map<String, Object> route) {
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

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
