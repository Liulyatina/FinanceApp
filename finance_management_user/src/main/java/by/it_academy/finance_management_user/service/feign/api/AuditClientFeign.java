package by.it_academy.finance_management_user.service.feign.api;

import by.it_academy.finance_management_user.service.feign.dto.AuditCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "auditClient", url = "http://localhost:8081/audit")
public interface AuditClientFeign {

    @PostMapping(produces = "application/json")
    void createAuditAction(@RequestBody AuditCreateDTO auditCreateDTO);
}
