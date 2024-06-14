package by.it_academy.finance_management_account.dao.api;

import by.it_academy.finance_management_account.dao.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAccountRepository extends JpaRepository<AccountEntity, UUID> {
}
