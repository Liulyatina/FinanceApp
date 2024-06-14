package by.it_academy.finance_management_user.dao.entity;

import by.it_academy.finance_management_user.core.enums.MailStatus;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "email_status", schema = "app")
public class MailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "mail", nullable = false, unique = true)
    private UserEntity mail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MailStatus status;

    public MailEntity() {
    }

    public MailEntity(UUID uuid, UserEntity mail, MailStatus status) {
        this.uuid = uuid;
        this.mail = mail;
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UserEntity getMail() {
        return mail;
    }

    public void setMail(UserEntity mail) {
        this.mail = mail;
    }

    public MailStatus getStatus() {
        return status;
    }

    public void setStatus(MailStatus status) {
        this.status = status;
    }
}
