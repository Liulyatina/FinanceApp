package by.it_academy.finance_management_account.service.api;

import by.it_academy.finance_management_account.dao.entity.AccountEntity;
import by.it_academy.finance_management_account.core.dto.PageOfAccountDTO;
import org.springframework.data.domain.Pageable;


import java.util.UUID;

public interface IAccountService {
    AccountEntity create(AccountEntity accountEntity);
    AccountEntity getById(UUID uuid);
    AccountEntity update(UUID uuid, AccountEntity accountEntity);
    PageOfAccountDTO getAll(Pageable pageable);
}