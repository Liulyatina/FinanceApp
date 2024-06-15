package by.it_academy.finance_management_user.dao.entity;

import by.it_academy.finance_management_user.core.enums.UserRole;
import by.it_academy.finance_management_user.core.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.Instant;

import java.util.UUID;
@Table(name = "users", schema = "app")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    @Column(unique = true)
    private String mail;
    private String fio;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String password;
    @Column(name = "dt_create", updatable = false)
    private Instant dt_create;
    @Column(name = "dt_update", nullable = false)
    private Instant dt_update;

    public UserEntity() {
    }

    public UserEntity(UUID uuid, String mail, String fio, UserRole role, UserStatus status, String password, Instant dtCreate, Instant dtUpdate) {
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;
        this.dt_create = dtCreate;
        this.dt_update = dtUpdate;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String email) {
        this.mail = email;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
