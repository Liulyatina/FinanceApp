package by.it_academy.finance_management_user.service.impl;
import by.it_academy.finance_management_user.core.enums.MailStatus;
import by.it_academy.finance_management_user.dao.entity.MailEntity;
import by.it_academy.finance_management_user.dao.entity.UserEntity;
import by.it_academy.finance_management_user.service.api.IMailSenderService;
import by.it_academy.finance_management_user.service.api.IMailService;
import by.it_academy.finance_management_user.service.api.IUserService;
import by.it_academy.finance_management_user.service.converter.MailConverter;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class MailSenderServiceImpl implements IMailSenderService {

    private final JavaMailSender mailSender;
    private final IMailService mailService;
    private final IUserService userService;
    private final MailConverter mailConverter;

    public MailSenderServiceImpl(JavaMailSender mailSender, IMailService mailService, IUserService userService, MailConverter mailConverter) {
        this.mailSender = mailSender;
        this.mailService = mailService;
        this.userService = userService;
        this.mailConverter = mailConverter;
    }

    @Override
    @Transactional
    public void sendVerificationEmail(String email, String verificationCode) {
        UserEntity userEntity = userService.findByMail(email)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с таким email не найден: " + email));

        MailEntity mailEntity = mailService.findByMail(email)
                .orElseGet(() -> new MailEntity(UUID.randomUUID(), userEntity, MailStatus.LOADED));

        if (mailEntity.getStatus() != MailStatus.OK) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setFrom("dzarankov@bk.ru");
            message.setSubject("Подтверждение регистрации");
            message.setText("Для подтверждения регистрации перейдите по следующей ссылке: " +
                    "https://localhost/api/v1/cabinet/verification?code=" + verificationCode + "&mail=" + email);

            try {
                mailSender.send(message);
                mailEntity.setStatus(MailStatus.OK);
                mailService.create(mailConverter.toDTO(mailEntity));
            } catch (MailException e) {
                mailEntity.setStatus(MailStatus.ERROR);
                mailService.create(mailConverter.toDTO(mailEntity));
            }
        }
    }
}