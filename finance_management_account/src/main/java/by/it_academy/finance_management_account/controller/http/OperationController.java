package by.it_academy.finance_management_account.controller.http;

import by.it_academy.finance_management_account.dao.entity.OperationEntity;
import by.it_academy.finance_management_account.service.api.IOperationService;
import by.it_academy.finance_management_account.service.api.dto.PageOfAccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/account/{uuid}/operation")
public class OperationController {

    private final IOperationService operationService;

    public OperationController(IOperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping
    public ResponseEntity<OperationEntity> create(@PathVariable UUID accountUuid, @RequestBody OperationEntity operationEntity) {
        OperationEntity createdOperation = operationService.create(accountUuid, operationEntity);
        return ResponseEntity.status(201).body(createdOperation);
    }

    @GetMapping
    public ResponseEntity<PageOfAccountDTO> getAll(@PathVariable UUID accountUuid,
                                                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                  @RequestParam(value = "size", defaultValue = "20") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        PageOfAccountDTO pageOfAccountDTO = operationService.getAll(accountUuid, pageable);
        return ResponseEntity.ok(pageOfAccountDTO);
    }


    @PutMapping("/{uuid_operation}")
    public ResponseEntity<OperationEntity> update(@PathVariable UUID accountUuid,
                                                           @PathVariable UUID operationUuid,
                                                           @RequestBody OperationEntity operationEntity) {
        OperationEntity updatedOperation = operationService.update(accountUuid, operationUuid, operationEntity);
        return ResponseEntity.ok(updatedOperation);
    }

    @DeleteMapping("/{uuid_operation}")
    public ResponseEntity<Void> delete(@PathVariable UUID accountUuid, @PathVariable UUID operationUuid) {
        operationService.delete(accountUuid, operationUuid);
        return ResponseEntity.noContent().build();
    }
}
