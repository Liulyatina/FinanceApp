package by.it_academy.finance_management_account.service.impl;

import by.it_academy.finance_management_account.dao.api.IAccountRepository;
import by.it_academy.finance_management_account.dao.entity.AccountEntity;
import by.it_academy.finance_management_account.service.api.IAccountService;
import by.it_academy.finance_management_account.service.api.dto.AccountDTO;
import by.it_academy.finance_management_account.service.api.dto.PageOfAccountDTO;
import by.it_academy.finance_management_account.service.converter.AccountConverter;
import by.it_academy.finance_management_account.service.feign.api.AuditClientFeign;
import by.it_academy.finance_management_account.service.feign.dto.AuditCreateDTO;
import by.it_academy.finance_management_account.service.feign.enums.TypeEntity;
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

    private final AuditClientFeign auditClient;

    public AccountServiceImpl(IAccountRepository accountRepository, AccountConverter converter, AuditClientFeign auditClient) {
        this.accountRepository = accountRepository;
        this.converter = converter;
        this.auditClient = auditClient;
    }

    @Override
    public AccountEntity create(AccountEntity accountEntity) {
        accountEntity.setAccountUuid(UUID.randomUUID());
        accountEntity = accountRepository.saveAndFlush(accountEntity);

        AuditCreateDTO auditCreateDTO = AuditCreateDTO.builder()
                .type(TypeEntity.ACCOUNT)
                .uuidUser(null)
                .uuidEntity(accountEntity.getAccountUuid())
                .text("Account created successfully")
                .build();

        auditClient.createAuditAction(auditCreateDTO);

        return accountEntity;
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
        accountEntity = accountRepository.saveAndFlush(accountEntity);

        AuditCreateDTO auditCreateDTO = AuditCreateDTO.builder()
                .type(TypeEntity.ACCOUNT)
                .uuidUser(null)
                .uuidEntity(accountEntity.getAccountUuid())
                .text("Account updated successfully")
                .build();

        auditClient.createAuditAction(auditCreateDTO);

        return accountEntity;
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
