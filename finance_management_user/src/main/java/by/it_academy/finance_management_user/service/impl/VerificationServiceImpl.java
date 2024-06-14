package by.it_academy.finance_management_user.service.impl;

import by.it_academy.finance_management_user.core.dto.VerificationDTO;
import by.it_academy.finance_management_user.dao.api.IVerificationRepository;
import by.it_academy.finance_management_user.dao.entity.UserEntity;
import by.it_academy.finance_management_user.dao.entity.VerificationEntity;
import by.it_academy.finance_management_user.service.api.IVerificationService;
import by.it_academy.finance_management_user.service.converter.VerificationConverter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationServiceImpl implements IVerificationService {

    private final IVerificationRepository verificationRepository;

    private final VerificationConverter converter;

    public VerificationServiceImpl(IVerificationRepository verificationRepository, VerificationConverter converter) {
        this.verificationRepository = verificationRepository;
        this.converter = converter;
    }

    @Override
    public VerificationEntity create(VerificationDTO verificationDTO) {
        VerificationEntity verificationEntity = converter.toEntity(verificationDTO);
        return verificationRepository.saveAndFlush(verificationEntity);
    }

    @Override
    public Optional<VerificationEntity> findByMail_MailAndVerificationCode(String mail, String code) {
        return verificationRepository.findByMail_MailAndVerificationCode(mail, code);
    }

    @Override
    public Optional<VerificationEntity> findByMail(UserEntity mail) {
        return verificationRepository.findByMail(mail);
    }
}