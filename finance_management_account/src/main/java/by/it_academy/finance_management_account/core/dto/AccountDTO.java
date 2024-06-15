package by.it_academy.finance_management_account.core.dto;

import by.it_academy.finance_management_account.core.AccountType;
import lombok.*;

import java.time.Instant;
import java.util.UUID;
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private UUID accountUuid;
    private Instant dt_create;
    private Instant dt_update;
    private String title;
    private String description;
    private Double balance;
    private AccountType type;
    private UUID currency;
}
