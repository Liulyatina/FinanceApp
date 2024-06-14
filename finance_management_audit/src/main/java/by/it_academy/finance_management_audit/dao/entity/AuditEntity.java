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

    @ManyToOne
    private UserEntity user;

    private String text;

    @Enumerated(EnumType.STRING)
    private EssenceType type;

    private String id;
    @Column(name = "dt_update", nullable = false)
    private Instant dtCreate;

    @PrePersist
    protected void onCreate() {
        dtCreate = Instant.now();
    }

    public AuditEntity() {
    }

    public AuditEntity(UUID uuid, UserEntity user, String text, EssenceType type, String id, Instant dtCreate) {
        this.uuid = uuid;
        this.user = user;
        this.text = text;
        this.type = type;
        this.id = id;
        this.dtCreate = dtCreate;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Instant dtCreate) {
        this.dtCreate = dtCreate;
    }
}

