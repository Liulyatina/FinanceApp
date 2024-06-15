package by.it_academy.finance_management_account.dao.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "operation", schema = "app")
public class OperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid")
    private UUID operationUuid;

    private Instant date;
    private String description;

    @Column(name = "dt_create", updatable = false)
    private Instant dt_create;

    @Column(name = "dt_update", nullable = false)
    private Instant dt_update;
    private UUID category;
    private Double value;
    private UUID currency;

    public OperationEntity() {}

    public OperationEntity(UUID operationUuid, Instant date, String description, Instant dt_create,
                           Instant dt_update, UUID category, Double value, UUID currency) {
        this.operationUuid = operationUuid;
        this.date = date;
        this.description = description;
        this.dt_create = dt_create;
        this.dt_update = dt_update;
        this.category = category;
        this.value = value;
        this.currency = currency;
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

    public UUID getOperationUuid() {
        return operationUuid;
    }

    public void setOperationUuid(UUID operationUuid) {
        this.operationUuid = operationUuid;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public UUID getCurrency() {
        return currency;
    }

    public void setCurrency(UUID currency) {
        this.currency = currency;
    }
}