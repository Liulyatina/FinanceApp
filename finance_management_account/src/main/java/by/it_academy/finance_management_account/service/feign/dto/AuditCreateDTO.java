package by.it_academy.finance_management_account.service.feign.dto;

import by.it_academy.finance_management_account.service.feign.enums.TypeEntity;
import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AuditCreateDTO {

    private UUID uuidUser;
    private String text;
    private TypeEntity type;
    private UUID uuidEntity;

}
