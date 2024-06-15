package by.it_academy.finance_management_audit.service.converter;

import by.it_academy.finance_management_audit.core.dto.AuditCreateDTO;
import by.it_academy.finance_management_audit.core.dto.AuditDTO;
import by.it_academy.finance_management_audit.dao.entity.AuditEntity;
import by.it_academy.finance_management_audit.service.api.IConverter;
import org.springframework.stereotype.Component;

@Component
public class AuditConverter implements IConverter<AuditCreateDTO, AuditEntity> {

    @Override
    public AuditEntity toEntity(AuditCreateDTO auditCreateDTO) {
        if (auditCreateDTO == null) {
            throw new IllegalArgumentException("AuditCreateDTO is null");
        }

        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setUserUuid(auditCreateDTO.getUuidUser());
        auditEntity.setType(auditCreateDTO.getType());
        auditEntity.setEntityId(auditCreateDTO.getUuidEntity());
        auditEntity.setText(auditCreateDTO.getText());

        return auditEntity;
    }

    public AuditDTO toDTO(AuditEntity auditEntity) {
        if (auditEntity == null) {
            throw new IllegalArgumentException("AuditEntity is null");
        }

        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setUuid(auditEntity.getUuid());
        auditDTO.setUuid(auditEntity.getUserUuid());
        auditDTO.setType(auditEntity.getType());
        auditDTO.setEntityId(auditEntity.getEntityId());
        auditDTO.setText(auditEntity.getText());
        auditDTO.setDtCreate(auditEntity.getDt_create());

        return auditDTO;
    }
}