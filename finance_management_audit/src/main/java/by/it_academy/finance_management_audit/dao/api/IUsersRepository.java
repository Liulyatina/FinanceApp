package by.it_academy.finance_management_audit.dao.api;

import by.it_academy.finance_management_audit.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUsersRepository extends JpaRepository<UserEntity, UUID> {
}
