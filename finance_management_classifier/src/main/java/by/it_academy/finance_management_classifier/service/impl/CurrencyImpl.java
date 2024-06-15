package by.it_academy.finance_management_classifier.service.impl;

import by.it_academy.finance_management_classifier.dao.api.ICurrencyRepository;
import by.it_academy.finance_management_classifier.dao.entity.CategoryEntity;
import by.it_academy.finance_management_classifier.dao.entity.CurrencyEntity;
import by.it_academy.finance_management_classifier.service.api.IClassifierService;
import by.it_academy.finance_management_classifier.service.api.converter.CurrencyConverter;
import by.it_academy.finance_management_classifier.service.api.dto.PageOfClassifierDTO;
import by.it_academy.finance_management_classifier.service.feign.api.AuditClientFeign;
import by.it_academy.finance_management_classifier.service.feign.dto.AuditCreateDTO;
import by.it_academy.finance_management_classifier.service.feign.enums.EssenceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CurrencyImpl implements IClassifierService<CurrencyEntity> {

    private final ICurrencyRepository currencyRepository;

    private final CurrencyConverter converter;

    private final AuditClientFeign auditClient;


    public CurrencyImpl(ICurrencyRepository currencyRepository, CurrencyConverter converter, AuditClientFeign auditClient) {
        this.currencyRepository = currencyRepository;
        this.converter = converter;
        this.auditClient = auditClient;
    }

    @Override
    @Transactional
    public CurrencyEntity create(CurrencyEntity currencyEntity) {
        currencyEntity.setUuid(UUID.randomUUID());
        currencyEntity = currencyRepository.saveAndFlush(currencyEntity);

        AuditCreateDTO auditCreateDTO = AuditCreateDTO.builder()
                .type(EssenceType.CURRENCY)
                .uuidUser(null)
                .uuidEntity(currencyEntity.getUuid())
                .text("Currency created successfully")
                .build();

        auditClient.createAuditAction(auditCreateDTO);

        return currencyEntity;
    }

    @Override
    public PageOfClassifierDTO getAll(Pageable pageable) {
        Page<CurrencyEntity> currencyEntities = currencyRepository.findAll(pageable);
        List<Object> currencyDTOs = currencyEntities.getContent().stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
        return PageOfClassifierDTO.builder()
                .number(currencyEntities.getNumber())
                .size(currencyEntities.getSize())
                .totalPages(currencyEntities.getTotalPages())
                .totalElements(currencyEntities.getTotalElements())
                .first(currencyEntities.isFirst())
                .numberOfElements(currencyEntities.getNumberOfElements())
                .last(currencyEntities.isLast())
                .content(currencyDTOs)
                .build();
    }
}