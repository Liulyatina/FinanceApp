package by.it_academy.finance_management_account.service.feign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;

@FeignClient(value = "currencyClient", url = "${app.currency-client.url}")
public interface CurrencyClientFeign {

    @GetMapping
    Boolean isExistCurrency(@RequestHeader("Authorization") String bearerToken,
                            @PathVariable("uuid") UUID uuid);
}
