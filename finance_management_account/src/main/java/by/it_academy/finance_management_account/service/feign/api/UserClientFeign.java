package by.it_academy.finance_management_account.service.feign.api;

import by.it_academy.finance_management_account.service.feign.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "userClient", url = "${app.user-client.url}")
public interface UserClientFeign {
    @GetMapping(produces = "application/json")
    UserDTO getMe(@RequestHeader("Authorization") String bearerToken);
}
