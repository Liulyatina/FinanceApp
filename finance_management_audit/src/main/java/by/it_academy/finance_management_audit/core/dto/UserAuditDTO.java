package by.it_academy.finance_management_audit.core.dto;


import by.it_academy.finance_management_audit.core.enums.UserRole;
import lombok.*;
import java.util.UUID;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserAuditDTO {

    private UUID uuid;
    private String mail;
    private String fio;
    private UserRole role;
}
