package by.it_academy.finance_management_user.service.impl;

import by.it_academy.finance_management_user.controller.utils.JwtTokenHandler;
import by.it_academy.finance_management_user.core.enums.UserRole;
import by.it_academy.finance_management_user.core.enums.UserStatus;
import by.it_academy.finance_management_user.dao.api.IEmailStatusRepository;
import by.it_academy.finance_management_user.core.dto.UserDTO;
import by.it_academy.finance_management_user.core.dto.UserLoginDTO;
import by.it_academy.finance_management_user.dao.api.IVerificationRepository;
import by.it_academy.finance_management_user.dao.entity.VerificationEntity;
import by.it_academy.finance_management_user.service.api.IUserService;
import by.it_academy.finance_management_user.service.converter.UserConverter;
import by.it_academy.finance_management_user.dao.api.IUsersRepository;
import by.it_academy.finance_management_user.dao.entity.UserEntity;
import by.it_academy.finance_management_user.service.api.IRegistrationService;
import by.it_academy.finance_management_user.core.dto.UserRegistrationDTO;
import by.it_academy.finance_management_user.service.feign.api.AuditClientFeign;
import by.it_academy.finance_management_user.service.feign.dto.AuditCreateDTO;
import by.it_academy.finance_management_user.service.feign.enums.TypeEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class RegistrationServiceImpl implements IRegistrationService {

    private final PasswordEncoder encoder;
    private final JwtTokenHandler jwtTokenHandler;
    private final IUsersRepository usersRepository;
    private final IUserService userService;
    private final IEmailStatusRepository emailStatusRepository;
    private final IVerificationRepository verificationRepository;
    private final UserConverter userConverter;
    private final UserHolder userHolder;
    private final AuditClientFeign auditClient;

    public RegistrationServiceImpl(PasswordEncoder encoder, JwtTokenHandler jwtTokenHandler,
                                   IUsersRepository usersRepository, IUserService userService,
                                   IEmailStatusRepository emailStatusRepository, IVerificationRepository verificationRepository,
                                   UserConverter userConverter, UserHolder userHolder, AuditClientFeign auditClient) {
        this.encoder = encoder;
        this.jwtTokenHandler = jwtTokenHandler;
        this.usersRepository = usersRepository;
        this.userService = userService;
        this.emailStatusRepository = emailStatusRepository;
        this.verificationRepository = verificationRepository;
        this.userConverter = userConverter;
        this.userHolder = userHolder;
        this.auditClient = auditClient;
    }

    @Override
    @Transactional
    public void registration(UserRegistrationDTO registrationDTO) {
        UserEntity userEntity = userConverter.toEntity(registrationDTO);
        userEntity.setUuid(UUID.randomUUID());
        userEntity.setRole(UserRole.USER);
        userEntity.setStatus(UserStatus.WAITING_ACTIVATION);
        userEntity.setPassword(registrationDTO.getPassword());

        userEntity = userService.create(userConverter.toDTO(userEntity));

        String verificationCode = UUID.randomUUID().toString();

        VerificationEntity verificationEntity = new VerificationEntity();
        verificationEntity.setMail(userEntity);
        verificationEntity.setVerificationCode(verificationCode);
        verificationRepository.saveAndFlush(verificationEntity);

        AuditCreateDTO auditCreateDTO = AuditCreateDTO.builder()
                .type(TypeEntity.USER)
                .uuidUser(userEntity.getUuid())
                .uuidEntity(userEntity.getUuid())
                .text("User registration successfully")
                .build();

        auditClient.createAuditAction(auditCreateDTO);

        userConverter.toDTO(userEntity);
    }

    @Override
    @Transactional
    public void verify(String code, String mail) {
        VerificationEntity verificationEntity = verificationRepository
                .findByMail_MailAndVerificationCode(mail, code)
                .orElseThrow(() -> new RuntimeException("Неверный код верификации"));

        UserEntity userEntity = verificationEntity.getMail();

        userEntity.setStatus(UserStatus.ACTIVATED);

        AuditCreateDTO auditCreateDTO = AuditCreateDTO.builder()
                .type(TypeEntity.USER)
                .uuidUser(userEntity.getUuid())
                .uuidEntity(userEntity.getUuid())
                .text("User verify successfully")
                .build();

        auditClient.createAuditAction(auditCreateDTO);

        usersRepository.saveAndFlush(userEntity);
        emailStatusRepository.deleteByMail_Mail(mail);
        verificationRepository.delete(verificationEntity);
    }

    @Override
    @Transactional
    public String login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getMail();

        UserEntity userEntity = usersRepository.findByMail(username)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));

        if (!userEntity.getStatus().equals(UserStatus.ACTIVATED)) {
            throw new IllegalArgumentException("Пользователь не активирован");
        }

        String encodedPassword = userEntity.getPassword();
        if (!encoder.matches(userLoginDTO.getPassword(), encodedPassword)) {
            throw new IllegalArgumentException("Логин или пароль неверный");
        }

        UserDetails userDetails = new User(userEntity.getMail(), userEntity.getPassword(), Collections.emptyList());
        String token = jwtTokenHandler.generateAccessToken(userDetails);

        AuditCreateDTO auditCreateDTO = AuditCreateDTO.builder()
                .type(TypeEntity.USER)
                .uuidUser(userEntity.getUuid())
                .uuidEntity(userEntity.getUuid())
                .text("User logged in successfully")
                .build();

        auditClient.createAuditAction(auditCreateDTO);

        return token;
    }


    @Override
    public UserDTO getInfo(String token) {
        UserDetails userDetails = userHolder.getUser();
        String email = userDetails.getUsername();

        UserEntity userEntity = usersRepository.findByMail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        AuditCreateDTO auditCreateDTO = AuditCreateDTO.builder()
                .type(TypeEntity.USER)
                .uuidUser(userEntity.getUuid())
                .uuidEntity(userEntity.getUuid())
                .text("User info accessed")
                .build();

        auditClient.createAuditAction(auditCreateDTO);

        return userConverter.toDTO(userEntity);
    }
}