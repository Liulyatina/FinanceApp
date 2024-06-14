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
    private Instant dtCreate;
    @Column(name = "dt_update", nullable = false)
    private Instant dtUpdate;

    public CategoryEntity() {
    }

    public CategoryEntity(UUID uuid, String title, Instant dtCreate, Instant dtUpdate) {
        this.uuid = uuid;
        this.title = title;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
    }

    @PrePersist
    protected void onCreate() {
        dtCreate = Instant.now();
        dtUpdate = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dtUpdate = Instant.now();
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

    public Instant getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Instant dtCreate) {
        this.dtCreate = dtCreate;
    }

    public Instant getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(Instant dtUpdate) {
        this.dtUpdate = dtUpdate;
    }
}
