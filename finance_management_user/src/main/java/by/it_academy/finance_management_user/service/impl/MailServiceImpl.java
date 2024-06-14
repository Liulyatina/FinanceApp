package by.it_academy.finance_management_user.service.impl;

import by.it_academy.finance_management_user.core.dto.MailDTO;
import by.it_academy.finance_management_user.dao.api.IEmailStatusRepository;
import by.it_academy.finance_management_user.dao.entity.MailEntity;
import by.it_academy.finance_management_user.service.api.IMailService;

import by.it_academy.finance_management_user.service.converter.MailConverter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MailServiceImpl implements IMailService {

    private final IEmailStatusRepository emailStatusRepository;

    private final MailConverter mailConverter;

    public MailServiceImpl(IEmailStatusRepository emailStatusRepository, MailConverter mailConverter) {
        this.emailStatusRepository = emailStatusRepository;
        this.mailConverter = mailConverter;
    }

    @Override
    public Optional<MailEntity> findByMail(String email) {
        return emailStatusRepository.findByMail_Mail(email);
    }

    @Override
    public void deleteByMail(String email) {
        emailStatusRepository.deleteByMail_Mail(email);
    }
    @Override
    public MailEntity create(MailDTO mailDTO) {
        MailEntity mailEntity = mailConverter.toEntity(mailDTO);
        return emailStatusRepository.saveAndFlush(mailEntity);
    }
}