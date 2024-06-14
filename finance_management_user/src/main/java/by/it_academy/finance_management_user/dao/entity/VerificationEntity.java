package by.it_academy.finance_management_user.dao.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "verification", schema = "app")
@Entity
public class VerificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    @ManyToOne
    @JoinColumn(name = "mail", nullable = false, unique = true)
    private UserEntity mail;

    @Column(name = "verification_code")
    private String verificationCode;

    public VerificationEntity() {
    }

    public VerificationEntity(UUID uuid, UserEntity mail, String verificationCode) {
        this.uuid = uuid;
        this.mail = mail;
        this.verificationCode = verificationCode;
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

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
