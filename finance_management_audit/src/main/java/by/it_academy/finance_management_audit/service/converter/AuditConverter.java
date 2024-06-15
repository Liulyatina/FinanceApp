package by.it_academy.finance_management_audit.service.converter;

import by.it_academy.finance_management_audit.dao.entity.AuditEntity;
import by.it_academy.finance_management_audit.service.api.IConverter;
import by.it_academy.finance_management_audit.core.dto.AuditDTO;
import org.springframework.stereotype.Component;

@Component
public class AuditConverter implements IConverter<AuditDTO, AuditEntity> {

    private final UserConverter userConverter;

    public AuditConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public AuditEntity toEntity(AuditDTO auditDTO) {
        if (auditDTO == null) {
            return null;
        }
        AuditEntity audit = new AuditEntity();
        audit.setUuid(auditDTO.getUuid());
        audit.setUser(userConverter.toEntity(auditDTO.getUser()));
        audit.setText(auditDTO.getText());
        audit.setType(auditDTO.getType());
        audit.setDtCreate(auditDTO.getDtCreate());
        return audit;
    }

    @Override
    public AuditDTO toDTO(AuditEntity auditEntity) {
        if (auditEntity == null) {
            return null;
        }
        AuditDTO auditDto = new AuditDTO();
        auditDto.setUuid(auditEntity.getUuid());
        auditDto.setUser(userConverter.toDTO(auditEntity.getUser()));
        auditDto.setText(auditEntity.getText());
        auditDto.setType(auditEntity.getType());
        auditDto.setDtCreate(auditEntity.getDtCreate());
        return auditDto;
    }
}
