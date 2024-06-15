package by.it_academy.finance_management_audit.service.impl;

import by.it_academy.finance_management_audit.core.dto.AuditCreateDTO;
import by.it_academy.finance_management_audit.core.dto.PageOfAuditDTO;
import by.it_academy.finance_management_audit.core.dto.UserAuditDTO;
import by.it_academy.finance_management_audit.dao.api.IAuditRepository;
import by.it_academy.finance_management_audit.dao.entity.AuditEntity;
import by.it_academy.finance_management_audit.service.api.IAuditService;
import by.it_academy.finance_management_audit.service.converter.AuditConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AuditService implements IAuditService {

    private final IAuditRepository auditRepository;
    private final AuditConverter converter;

    public AuditService(IAuditRepository auditRepository, AuditConverter converter) {
        this.auditRepository = auditRepository;
        this.converter = converter;
    }

    @Override
    @Transactional
    public void create(AuditCreateDTO auditCreateDTO) {
        AuditEntity audit = converter.toEntity(auditCreateDTO);
        auditRepository.save(audit);
    }

    @Override
    @Transactional
    public AuditEntity getAuditById(UUID uuid) {
        Optional<AuditEntity> audit = this.auditRepository.findById(uuid);
        if (audit.isEmpty()) {
            throw new IllegalArgumentException("Действие с таким id существует");
        }
        return audit.get();
    }

    @Override
    @Transactional
    public PageOfAuditDTO getAll(Pageable pageable) {
        Page<AuditEntity> auditEntities = auditRepository.findAll(pageable);
        List<Object> auditDTOs = auditEntities.getContent().stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
        return PageOfAuditDTO.builder()
                .number(auditEntities.getNumber())
                .size(auditEntities.getSize())
                .totalPages(auditEntities.getTotalPages())
                .totalElements(auditEntities.getTotalElements())
                .first(auditEntities.isFirst())
                .numberOfElements(auditEntities.getNumberOfElements())
                .last(auditEntities.isLast())
                .content(auditDTOs)
                .build();
    }
}
