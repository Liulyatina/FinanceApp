package by.it_academy.finance_management_account.service.feign.api;

import by.it_academy.finance_management_account.service.feign.dto.AuditCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "auditClient", url = "${app.audit-client.url}")
public interface AuditClientFeign {

    @PostMapping(produces = "application/json")
    void createAuditAction(@RequestBody AuditCreateDTO auditCreateDTO);
}
