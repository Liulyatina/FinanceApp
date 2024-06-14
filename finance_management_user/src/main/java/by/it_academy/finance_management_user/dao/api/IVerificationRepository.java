package by.it_academy.finance_management_user.dao.api;

import by.it_academy.finance_management_user.dao.entity.UserEntity;
import by.it_academy.finance_management_user.dao.entity.VerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IVerificationRepository extends JpaRepository<VerificationEntity, UUID> {
    Optional<VerificationEntity> findByMail_MailAndVerificationCode(String mail, String code);

    Optional<VerificationEntity> findByMail(UserEntity mail);


}
