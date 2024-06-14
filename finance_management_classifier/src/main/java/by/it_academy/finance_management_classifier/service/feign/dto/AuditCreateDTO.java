package by.it_academy.finance_management_classifier.service.feign.dto;

import by.it_academy.finance_management_classifier.service.feign.enums.ETypeEntity;
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
    private ETypeEntity type;
    private UUID uuidEntity;

}
