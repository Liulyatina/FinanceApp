package by.it_academy.finance_management_audit.dao.api;

import by.it_academy.finance_management_audit.dao.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IAuditRepository extends JpaRepository<AuditEntity, UUID> {

    Optional<AuditEntity> findByUuid (UUID uuid);


}
