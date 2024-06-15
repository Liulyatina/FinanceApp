package by.it_academy.finance_management_user.service.scheduler;

import by.it_academy.finance_management_user.core.enums.MailStatus;
import by.it_academy.finance_management_user.dao.api.IEmailStatusRepository;
import by.it_academy.finance_management_user.dao.api.IVerificationRepository;
import by.it_academy.finance_management_user.dao.entity.MailEntity;
import by.it_academy.finance_management_user.dao.entity.VerificationEntity;
import by.it_academy.finance_management_user.service.api.IMailSenderService;
import by.it_academy.finance_management_user.dao.api.IUsersRepository;
import by.it_academy.finance_management_user.dao.entity.UserEntity;
import by.it_academy.finance_management_user.core.enums.UserStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MailScheduler {

    private final IUsersRepository usersRepository;
    private final IEmailStatusRepository emailStatusRepository;
    private final IMailSenderService mailSenderService;
    private final IVerificationRepository verificationRepository;

    public MailScheduler(IUsersRepository usersRepository, IEmailStatusRepository emailStatusRepository, IMailSenderService mailSenderService, IVerificationRepository verificationRepository) {
        this.usersRepository = usersRepository;
        this.emailStatusRepository = emailStatusRepository;
        this.mailSenderService = mailSenderService;
        this.verificationRepository = verificationRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void sendVerificationEmails() {
        List<UserEntity> users = usersRepository.findByStatus(UserStatus.WAITING_ACTIVATION);

        for (UserEntity user : users) {
            Optional<VerificationEntity> optionalVerificationEntity = verificationRepository.findByMail(user);
            if (optionalVerificationEntity.isPresent()) {
                VerificationEntity verificationEntity = optionalVerificationEntity.get();
                MailEntity emailStatus = emailStatusRepository.findByMail_Mail(user.getMail())
                        .orElse(new MailEntity());

                if (emailStatus.getStatus() != MailStatus.OK) {
                    mailSenderService.sendVerificationEmail(user.getMail(), verificationEntity.getVerificationCode());
                }
            }
        }
    }
}
