package by.it_academy.finance_management_user.service.api;

import by.it_academy.finance_management_user.core.dto.PageOfUserDTO;
import by.it_academy.finance_management_user.dao.entity.UserEntity;
import by.it_academy.finance_management_user.core.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import java.time.Instant;


import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    UserEntity create(UserDTO userDTO);
    UserEntity getById(UUID uuid);
    void  delete(UUID uuid);
    UserEntity update(UUID uuid, Instant dtUpdate, UserDTO userDTO);
    PageOfUserDTO getAll(Pageable pageable);
    Optional<UserEntity> findByMail(String email);
}
