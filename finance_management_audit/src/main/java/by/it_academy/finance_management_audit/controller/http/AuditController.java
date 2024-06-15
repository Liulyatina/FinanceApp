package by.it_academy.finance_management_audit.controller.http;

import by.it_academy.finance_management_audit.core.dto.AuditCreateDTO;
import by.it_academy.finance_management_audit.core.dto.PageOfAuditDTO;
import by.it_academy.finance_management_audit.core.dto.UserAuditDTO;
import by.it_academy.finance_management_audit.dao.entity.AuditEntity;
import by.it_academy.finance_management_audit.core.dto.AuditDTO;
import by.it_academy.finance_management_audit.service.converter.AuditConverter;
import by.it_academy.finance_management_audit.service.impl.AuditService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/audit", produces = "application/json; charset=UTF-8")
public class AuditController {

    private final AuditService auditService;
    private final AuditConverter auditConverter;

    public AuditController(AuditService auditService, AuditConverter auditConverter) {
        this.auditService = auditService;
        this.auditConverter = auditConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AuditCreateDTO userAuditDTO){

        this.auditService.create(userAuditDTO);
    }

    @GetMapping
    public ResponseEntity<PageOfAuditDTO> getAllAudits(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "20") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        PageOfAuditDTO pageOfAuditDTO = auditService.getAll(pageable);
        return ResponseEntity.ok(pageOfAuditDTO);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<AuditDTO> getAuditById(@PathVariable("uuid") UUID uuid) {
        AuditDTO user = auditConverter.toDTO(auditService.getAuditById(uuid));
        return ResponseEntity.ok(user);
    }

}
