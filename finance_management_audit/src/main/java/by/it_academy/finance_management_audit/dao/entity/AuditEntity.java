package by.it_academy.finance_management_audit.dao.entity;

import by.it_academy.finance_management_audit.core.enums.EssenceType;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "audit", schema = "app")
public class AuditEntity {
    @Id
    @GeneratedValue
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "text", nullable = false)
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EssenceType type;

    @Column(name = "dt_create", nullable = false)
    private Instant dtCreate;

    @Column(name = "entity_id")
    private UUID entityId;

    @PrePersist
    protected void onCreate() {
        dtCreate = Instant.now();
    }

    public AuditEntity() {
    }

    public AuditEntity(UUID uuid, UserEntity user, String text, EssenceType type, Instant dtCreate, UUID entityId) {
        this.uuid = uuid;
        this.user = user;
        this.text = text;
        this.type = type;
        this.dtCreate = dtCreate;
        this.entityId = entityId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EssenceType getType() {
        return type;
    }

    public void setType(EssenceType type) {
        this.type = type;
    }

    public Instant getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Instant dtCreate) {
        this.dtCreate = dtCreate;
    }

    public UUID getEntityId() {
        return entityId;
    }

    public void setEntityId(UUID entityId) {
        this.entityId = entityId;
    }
}

