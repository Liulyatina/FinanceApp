package by.it_academy.finance_management_user.service.api;

public interface IMailSenderService {

    void sendVerificationEmail(String to, String verificationCode);
}
