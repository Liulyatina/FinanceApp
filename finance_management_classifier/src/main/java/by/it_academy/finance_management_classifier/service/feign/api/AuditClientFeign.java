package by.it_academy.finance_management_classifier.service.feign.api;

import by.it_academy.finance_management_classifier.service.feign.dto.AuditCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "auditClient", url = "${app.audit-client.url}")
public interface AuditClientFeign {

    @PostMapping(produces = "application/json")
    void createAuditAction(@RequestHeader("Authorization") String bearerToken,
                           @RequestBody AuditCreateDTO auditCreateDTO);
}
