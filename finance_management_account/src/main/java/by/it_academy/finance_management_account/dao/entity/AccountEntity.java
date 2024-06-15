package by.it_academy.finance_management_account.dao.entity;

import by.it_academy.finance_management_account.core.AccountType;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "account", schema = "app")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid")
    private UUID accountUuid;
    private String title;
    private String description;
    private Double balance;

    @Column(name = "dt_create", updatable = false)
    private Instant dt_create;

    @Column(name = "dt_update", nullable = false)
    private Instant dt_update;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private UUID currency;

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

    public AccountEntity() {}

    public AccountEntity(UUID accountUuid, String title, String description,
                         Double balance, Instant dt_create, Instant dt_update,
                         AccountType type, UUID currency) {
        this.accountUuid = accountUuid;
        this.title = title;
        this.description = description;
        this.balance = balance;
        this.dt_create = dt_create;
        this.dt_update = dt_update;
        this.type = type;
        this.currency = currency;
    }

    public UUID getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(UUID accountUuid) {
        this.accountUuid = accountUuid;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public UUID getCurrency() {
        return currency;
    }

    public void setCurrency(UUID currency) {
        this.currency = currency;
    }
}