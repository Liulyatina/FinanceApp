package by.it_academy.finance_management_user.service.impl;

import by.it_academy.finance_management_user.core.dto.PageOfUserDTO;
import by.it_academy.finance_management_user.dao.api.IUsersRepository;
import by.it_academy.finance_management_user.dao.entity.UserEntity;
import by.it_academy.finance_management_user.service.api.IUserService;
import by.it_academy.finance_management_user.core.dto.UserDTO;
import by.it_academy.finance_management_user.service.converter.UserConverter;
import by.it_academy.finance_management_user.service.feign.api.AuditClientFeign;
import by.it_academy.finance_management_user.service.feign.dto.AuditCreateDTO;
import by.it_academy.finance_management_user.service.feign.enums.TypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements IUserService {

    private final IUsersRepository userRepository;
    private final UserConverter converter;
    private final PasswordEncoder encoder;
    private final AuditClientFeign auditClient;

    public UserServiceImpl(IUsersRepository userRepository, UserConverter converter, PasswordEncoder encoder, AuditClientFeign auditClient) {
        this.userRepository = userRepository;
        this.converter = converter;
        this.encoder = encoder;
        this.auditClient = auditClient;
    }

    @Override
    @Transactional
    public UserEntity create(UserDTO userDTO) {
        userDTO.setUuid(UUID.randomUUID());
        String encodedPassword = encoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        UserEntity userEntity = converter.toEntity(userDTO);

        userEntity = userRepository.saveAndFlush(userEntity);

        AuditCreateDTO auditCreateDTO = AuditCreateDTO.builder()
                .type(TypeEntity.USER)
                .uuidUser(userEntity.getUuid())
                .uuidEntity(userEntity.getUuid())
                .text("User created successfully")
                .build();

        auditClient.createAuditAction(auditCreateDTO);

        return userEntity;
    }

    @Override
    @Transactional
    public UserEntity getById(UUID uuid) {
        return userRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        UserEntity userEntity = userRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        userRepository.deleteById(uuid);

        AuditCreateDTO auditCreateDTO = AuditCreateDTO.builder()
                .type(TypeEntity.USER)
                .uuidUser(userEntity.getUuid())
                .uuidEntity(userEntity.getUuid())
                .text("User deleted successfully")
                .build();

        auditClient.createAuditAction(auditCreateDTO);
    }

    @Override
    @Transactional
    public UserEntity update(UUID uuid, Instant udtUpdate, UserDTO userDTO) {
        UserEntity existingUser = userRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        existingUser.setMail(userDTO.getMail());
        existingUser.setFio(userDTO.getFio());
        existingUser.setRole(userDTO.getRole());
        existingUser.setStatus(userDTO.getStatus());

        if (userDTO.getPassword() != null) {
            existingUser.setPassword(encoder.encode(userDTO.getPassword()));
        }

        existingUser = userRepository.saveAndFlush(existingUser);

        AuditCreateDTO auditCreateDTO = AuditCreateDTO.builder()
                .type(TypeEntity.USER)
                .uuidUser(existingUser.getUuid())
                .uuidEntity(existingUser.getUuid())
                .text("User updated successfully")
                .build();

        auditClient.createAuditAction(auditCreateDTO);

        return existingUser;
    }

@Override
    public PageOfUserDTO getAll(Pageable pageable) {
        Page<UserEntity> userEntities = userRepository.findAll(pageable);
        List<UserDTO> userDTOs = userEntities.getContent().stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
        return PageOfUserDTO.builder()
                .number(userEntities.getNumber())
                .size(userEntities.getSize())
                .totalPages(userEntities.getTotalPages())
                .totalElements(userEntities.getTotalElements())
                .first(userEntities.isFirst())
                .numberOfElements(userEntities.getNumberOfElements())
                .last(userEntities.isLast())
                .content(userDTOs)
                .build();
    }

    @Override
    public Optional<UserEntity> findByMail(String email) {
        return userRepository.findByMail(email);
    }
}
