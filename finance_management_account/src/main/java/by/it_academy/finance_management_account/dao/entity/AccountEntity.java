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
    private Instant dtCreate;

    @Column(name = "dt_update", nullable = false)
    private Instant dtUpdate;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private UUID currency;

    @PrePersist
    protected void onCreate() {
        dtCreate = Instant.now();
        dtUpdate = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dtUpdate = Instant.now();
    }

    public AccountEntity() {}

    public AccountEntity(UUID accountUuid, String title, String description, Double balance, Instant dtCreate, Instant dtUpdate, AccountType type, UUID currency) {
        this.accountUuid = accountUuid;
        this.title = title;
        this.description = description;
        this.balance = balance;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
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