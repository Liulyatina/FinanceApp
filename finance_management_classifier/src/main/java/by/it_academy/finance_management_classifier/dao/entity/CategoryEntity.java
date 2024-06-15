package by.it_academy.finance_management_classifier.dao.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Table(name = "operation_category", schema = "app")
@Entity
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private String title;
    @Column(name = "dt_create", updatable = false)
    private Instant dt_create;
    @Column(name = "dt_update", nullable = false)
    private Instant dt_update;

    public CategoryEntity() {
    }

    public CategoryEntity(UUID uuid, String title, Instant dt_create, Instant dt_update) {
        this.uuid = uuid;
        this.title = title;
        this.dt_create = dt_create;
        this.dt_update = dt_update;
    }

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        dt_create = now;
        dt_update = now;
    }

    @PreUpdate
    protected void onUpdate() {
        dt_update = Instant.now();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getDt_create() {
        return dt_create;
    }

    public void setDt_create(Instant dt_create) {
        this.dt_create = dt_create;
    }

    public Instant getDt_update() {
        return dt_update;
    }

    public void setDt_update(Instant dt_update) {
        this.dt_update = dt_update;
    }
}
