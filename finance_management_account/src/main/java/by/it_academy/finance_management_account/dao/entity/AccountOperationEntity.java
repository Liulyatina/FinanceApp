package by.it_academy.finance_management_account.dao.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "account_operation", schema = "app")
public class AccountOperationEntity {
    @Id
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "account_uuid", referencedColumnName = "uuid")
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name = "operation_uuid", referencedColumnName = "uuid")
    private OperationEntity operation;

    public AccountOperationEntity() {
    }

    public AccountOperationEntity(UUID uuid, AccountEntity account, OperationEntity operation) {
        this.uuid = uuid;
        this.account = account;
        this.operation = operation;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public OperationEntity getOperation() {
        return operation;
    }

    public void setOperation(OperationEntity operation) {
        this.operation = operation;
    }
}