package by.it_academy.finance_management_user.service.api;

import by.it_academy.finance_management_user.core.dto.VerificationDTO;
import by.it_academy.finance_management_user.dao.entity.UserEntity;
import by.it_academy.finance_management_user.dao.entity.VerificationEntity;

import java.util.Optional;

public interface IVerificationService {

    VerificationEntity create(VerificationDTO verificationDTO);

    Optional<VerificationEntity> findByMail_MailAndVerificationCode(String mail, String code);

    Optional<VerificationEntity> findByMail(UserEntity mail);
}