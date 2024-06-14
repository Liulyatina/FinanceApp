package by.it_academy.finance_management_audit.core.dto;

import by.it_academy.finance_management_audit.core.enums.EssenceType;
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
public class AuditDTO {

    private UUID uuid;

    private UserAuditDTO user;

    private String text;

    private EssenceType type;

    private String id;

    private Instant dtCreate;
}
