package by.it_academy.finance_management_user.dao.api;

import by.it_academy.finance_management_user.dao.entity.MailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IEmailStatusRepository extends JpaRepository<MailEntity, UUID> {

    Optional<MailEntity> findByMail_Mail(String email);

    void deleteByMail_Mail(String email);

}
