package by.it_academy.finance_management_user.service.impl;

import by.it_academy.finance_management_user.core.dto.PageOfUserDTO;
import by.it_academy.finance_management_user.dao.api.IUsersRepository;
import by.it_academy.finance_management_user.dao.entity.UserEntity;
import by.it_academy.finance_management_user.service.api.IUserService;
import by.it_academy.finance_management_user.core.dto.UserDTO;
import by.it_academy.finance_management_user.service.converter.UserConverter;
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

    public UserServiceImpl(IUsersRepository userRepository, UserConverter converter, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.converter = converter;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public UserEntity create(UserDTO userDTO) {
        userDTO.setUuid(UUID.randomUUID());
        String encodedPassword = encoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        UserEntity userEntity = converter.toEntity(userDTO);



        return userRepository.saveAndFlush(userEntity);
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
        userRepository.deleteById(uuid);
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
        return userRepository.saveAndFlush(existingUser);
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
