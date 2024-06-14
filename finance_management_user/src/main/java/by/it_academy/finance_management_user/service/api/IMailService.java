package by.it_academy.finance_management_user.service.api;

import by.it_academy.finance_management_user.core.dto.MailDTO;
import by.it_academy.finance_management_user.dao.entity.MailEntity;

import java.util.Optional;

public interface IMailService {
    Optional<MailEntity> findByMail(String email);
    void deleteByMail(String email);
    MailEntity create(MailDTO mailDTO);

}