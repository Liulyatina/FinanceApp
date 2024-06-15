package by.it_academy.finance_management_audit.service.api;


import by.it_academy.finance_management_audit.core.dto.AuditCreateDTO;
import by.it_academy.finance_management_audit.core.dto.AuditDTO;
import by.it_academy.finance_management_audit.core.dto.PageOfAuditDTO;
import by.it_academy.finance_management_audit.core.dto.UserAuditDTO;
import by.it_academy.finance_management_audit.dao.entity.AuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IAuditService {
    void create(AuditCreateDTO auditCreateDTO);
    AuditEntity getAuditById(UUID uuid);
    PageOfAuditDTO getAll(Pageable pageable);
}
