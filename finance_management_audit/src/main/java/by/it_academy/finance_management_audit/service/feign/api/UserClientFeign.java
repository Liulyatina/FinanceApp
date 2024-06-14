package by.it_academy.finance_management_audit.service.feign.api;


import by.it_academy.finance_management_audit.service.feign.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;

@FeignClient(value = "userClientFeign", url = "${app.user-client.url}")
public interface UserClientFeign {

    @GetMapping(value = "/users/{uuid}", produces = "application/json")
    ResponseEntity<UserDTO> get(@RequestHeader("Authorization") String bearerToken,
                                @PathVariable("uuid") UUID uuid);

    @GetMapping(value = "/cabinet/me", produces = "application/json")
    ResponseEntity<UserDTO> aboutUser(@RequestHeader("Authorization") String bearerToken);

}
