package by.it_academy.finance_management_classifier.dao.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Table(name = "currency", schema = "app")
@Entity
public class CurrencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private String title;
    private String description;

    @Column(name = "dt_create", updatable = false)
    private Instant dtCreate;
    @Column(name = "dt_update", nullable = false)
    private Instant dtUpdate;

    public CurrencyEntity() {
    }

    public CurrencyEntity(UUID uuid, String title, String description, Instant dtCreate, Instant dtUpdate) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
