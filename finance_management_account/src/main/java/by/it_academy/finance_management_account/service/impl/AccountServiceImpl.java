package by.it_academy.finance_management_account.service.impl;

import by.it_academy.finance_management_account.dao.api.IAccountRepository;
import by.it_academy.finance_management_account.dao.entity.AccountEntity;
import by.it_academy.finance_management_account.service.api.IAccountService;
import by.it_academy.finance_management_account.service.api.dto.AccountDTO;
import by.it_academy.finance_management_account.service.api.dto.PageOfAccountDTO;
import by.it_academy.finance_management_account.service.converter.AccountConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements IAccountService {

    private final IAccountRepository accountRepository;

    private final AccountConverter converter;

    public AccountServiceImpl(IAccountRepository accountRepository, AccountConverter converter) {
        this.accountRepository = accountRepository;
        this.converter = converter;
    }

    @Override
    public AccountEntity create(AccountEntity accountEntity) {
        accountEntity.setAccountUuid(UUID.randomUUID());
        return accountRepository.saveAndFlush(accountEntity);
    }

    @Override
    public AccountEntity getById(UUID uuid) {
        return accountRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Счёт не найден"));
    }

    @Override
    public AccountEntity update(UUID uuid, AccountEntity accountEntity) {
        AccountEntity existingAccount = accountRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Счёт не найден"));
        accountEntity.setAccountUuid(uuid);
        return accountRepository.saveAndFlush(accountEntity);
    }

    @Override
    public PageOfAccountDTO getAll(Pageable pageable) {
        Page<AccountEntity> accountEntities = accountRepository.findAll(pageable);
        List<Object> accountDTOs = accountEntities.getContent().stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
        return PageOfAccountDTO.builder()
                .number(accountEntities.getNumber())
                .size(accountEntities.getSize())
                .totalPages(accountEntities.getTotalPages())
                .totalElements(accountEntities.getTotalElements())
                .first(accountEntities.isFirst())
                .numberOfElements(accountEntities.getNumberOfElements())
                .last(accountEntities.isLast())
                .content(accountDTOs)
                .build();
    }
}
