package by.it_academy.finance_management_account.service.api;

import by.it_academy.finance_management_account.dao.entity.AccountEntity;
import by.it_academy.finance_management_account.dao.entity.OperationEntity;
import by.it_academy.finance_management_account.service.api.dto.PageOfAccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IOperationService {
    OperationEntity create(UUID accountUuid, OperationEntity operationEntity);

    void delete(UUID accountUuid, UUID operationUuid);

    OperationEntity update(UUID accountUuid, UUID operationUuid, OperationEntity operationEntity);

    PageOfAccountDTO getAll(UUID operationUuid, Pageable pageable);

}
