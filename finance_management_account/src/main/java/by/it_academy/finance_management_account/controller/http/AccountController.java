package by.it_academy.finance_management_account.controller.http;

import by.it_academy.finance_management_account.dao.entity.AccountEntity;
import by.it_academy.finance_management_account.service.api.IAccountService;
import by.it_academy.finance_management_account.service.api.dto.PageOfAccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountEntity> create(@RequestBody AccountEntity accountEntity) {
        AccountEntity createdAccount = accountService.create(accountEntity);
        return ResponseEntity.status(201).body(createdAccount);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<AccountEntity> getAccountById(@PathVariable UUID uuid) {
        AccountEntity accountEntity = accountService.getById(uuid);
        return ResponseEntity.ok(accountEntity);
    }

    @GetMapping
    public ResponseEntity<PageOfAccountDTO> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "20") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        PageOfAccountDTO pageOfAccountDTO = accountService.getAll(pageable);
        return ResponseEntity.ok(pageOfAccountDTO);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<AccountEntity> update(@PathVariable UUID uuid,
                                                       @RequestBody AccountEntity accountEntity) {
        AccountEntity updatedAccount = accountService.update(uuid, accountEntity);
        return ResponseEntity.ok(updatedAccount);
    }
}
