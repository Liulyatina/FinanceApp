package by.it_academy.finance_management_user.dao.api;

import by.it_academy.finance_management_user.core.enums.UserStatus;
import by.it_academy.finance_management_user.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUsersRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByMail(String email);
    List<UserEntity> findByStatus(UserStatus status);
}
