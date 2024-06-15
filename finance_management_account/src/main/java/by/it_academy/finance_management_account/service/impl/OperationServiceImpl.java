package by.it_academy.finance_management_account.service.impl;

import by.it_academy.finance_management_account.dao.api.IAccountOperationRepository;
import by.it_academy.finance_management_account.dao.api.IOperationRepository;
import by.it_academy.finance_management_account.dao.entity.AccountEntity;
import by.it_academy.finance_management_account.dao.entity.OperationEntity;
import by.it_academy.finance_management_account.service.api.IOperationService;
import by.it_academy.finance_management_account.service.api.dto.OperationDTO;
import by.it_academy.finance_management_account.service.api.dto.PageOfAccountDTO;
import by.it_academy.finance_management_account.service.converter.OperationConverter;
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
@Transactional(readOnly = true)
public class OperationServiceImpl implements IOperationService {

    private final IOperationRepository  operationRepository;

    private final IAccountOperationRepository accountOperationRepository;

    private final OperationConverter converter;

    private final AuditClientFeign auditClient;

    public OperationServiceImpl(IOperationRepository operationRepository, IAccountOperationRepository accountOperationRepository,
                                OperationConverter converter, AuditClientFeign auditClient) {
        this.operationRepository = operationRepository;
        this.accountOperationRepository = accountOperationRepository;
        this.converter = converter;
        this.auditClient = auditClient;
    }

    @Override
    @Transactional
    public OperationEntity create(UUID accountUuid, OperationEntity operationEntity){
        operationEntity.setOperationUuid(UUID.randomUUID());
        operationEntity = operationRepository.saveAndFlush(operationEntity);

        AuditCreateDTO auditCreateDTO = AuditCreateDTO.builder()
                .type(TypeEntity.OPERATION)
                .uuidUser(null)
                .uuidEntity(operationEntity.getOperationUuid())
                .text("Operation created successfully")
                .build();

        auditClient.createAuditAction(auditCreateDTO);

        return operationEntity;
    }

    @Override
    @Transactional
    public void delete(UUID accountUuid, UUID operationUuid) {
        OperationEntity operation = accountOperationRepository.findByAccount_AccountUuidAndOperation_OperationUuid(accountUuid, operationUuid)
                .orElseThrow(() -> new RuntimeException("Операция не найдена"));
        operationRepository.delete(operation);

        AuditCreateDTO auditCreateDTO = AuditCreateDTO.builder()
                .type(TypeEntity.OPERATION)
                .uuidUser(null)
                .uuidEntity(operationUuid)
                .text("Operation deleted successfully")
                .build();

        auditClient.createAuditAction(auditCreateDTO);
    }

    @Override
    @Transactional
    public OperationEntity update(UUID accountUuid, UUID operationUuid, OperationEntity operationEntity) {
        OperationEntity existingOperation = accountOperationRepository.findByAccount_AccountUuidAndOperation_OperationUuid(accountUuid, operationUuid)
                .orElseThrow(() -> new RuntimeException("Операция не найдена"));

        existingOperation.setDescription(operationEntity.getDescription());
        existingOperation.setCategory(operationEntity.getCategory());
        existingOperation.setValue(operationEntity.getValue());
        existingOperation.setCurrency(operationEntity.getCurrency());

        existingOperation = operationRepository.saveAndFlush(existingOperation);

        AuditCreateDTO auditCreateDTO = AuditCreateDTO.builder()
                .type(TypeEntity.OPERATION)
                .uuidUser(null)
                .uuidEntity(existingOperation.getOperationUuid())
                .text("Operation updated successfully")
                .build();

        auditClient.createAuditAction(auditCreateDTO);

        return existingOperation;
    }
    @Override
    public PageOfAccountDTO getAll(UUID operationUuid, Pageable pageable) {
        Page<OperationEntity> operationEntities = operationRepository.findAll(pageable);
        List<Object> operationDTOs = operationEntities.getContent().stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
        return PageOfAccountDTO.builder()
                .number(operationEntities.getNumber())
                .size(operationEntities.getSize())
                .totalPages(operationEntities.getTotalPages())
                .totalElements(operationEntities.getTotalElements())
                .first(operationEntities.isFirst())
                .numberOfElements(operationEntities.getNumberOfElements())
                .last(operationEntities.isLast())
                .content(operationDTOs)
                .build();
    }
}