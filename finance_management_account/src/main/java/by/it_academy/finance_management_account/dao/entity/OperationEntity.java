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
    private Instant dtCreate;

    @Column(name = "dt_update", nullable = false)
    private Instant dtUpdate;

    private UUID category;
    private Double value;
    private UUID currency;

    public OperationEntity() {}

    public OperationEntity(UUID operationUuid, Instant date, String description, Instant dtCreate,
                           Instant dtUpdate, UUID category, Double value, UUID currency) {
        this.operationUuid = operationUuid;
        this.date = date;
        this.description = description;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.category = category;
        this.value = value;
        this.currency = currency;
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