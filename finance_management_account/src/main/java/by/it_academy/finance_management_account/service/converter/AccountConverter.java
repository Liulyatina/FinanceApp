package by.it_academy.finance_management_account.service.converter;

import by.it_academy.finance_management_account.dao.entity.AccountEntity;
import by.it_academy.finance_management_account.service.api.IConverter;
import by.it_academy.finance_management_account.service.api.dto.AccountDTO;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter implements IConverter<AccountDTO, AccountEntity> {

    @Override
    public AccountEntity toEntity(AccountDTO accountDTO) {
        if (accountDTO == null) {
            return null;
        }
        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setAccountUuid(accountDTO.getAccountUuid());
        accountEntity.setDescription(accountDTO.getDescription());
        accountEntity.setBalance(accountDTO.getBalance());
        accountEntity.setCurrency(accountDTO.getCurrency());
        accountEntity.setTitle(accountDTO.getTitle());
        accountEntity.setType(accountDTO.getType());

        return accountEntity;
    }

    @Override
    public AccountDTO toDTO(AccountEntity accountEntity) {
        if (accountEntity == null) {
            return null;
        }
        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setAccountUuid(accountEntity.getAccountUuid());
        accountDTO.setBalance(accountEntity.getBalance());
        accountDTO.setCurrency(accountEntity.getCurrency());
        accountDTO.setDescription(accountEntity.getDescription());
        accountDTO.setType(accountEntity.getType());

        return accountDTO;

    }
}
