package by.it_academy.finance_management_account.dao.api;

import by.it_academy.finance_management_account.dao.entity.AccountOperationEntity;
import by.it_academy.finance_management_account.dao.entity.OperationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IAccountOperationRepository extends JpaRepository<AccountOperationEntity, UUID> {

    Optional<OperationEntity> findByAccount_AccountUuidAndOperation_OperationUuid(UUID accountUuid, UUID operationUuid);

    Page<OperationEntity> findByAccount_AccountUuid(UUID accountUuid, Pageable pageable);
}
