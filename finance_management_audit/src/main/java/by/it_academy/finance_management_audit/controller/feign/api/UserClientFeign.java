package by.it_academy.finance_management_audit.controller.feign.api;


import by.it_academy.finance_management_audit.core.dto.UserAuditDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "userClient", url = "${app.user-client.url}")
public interface UserClientFeign {
    @GetMapping(produces = "application/json")
    UserAuditDTO getMe(@RequestHeader("Authorization") String bearerToken);
}
