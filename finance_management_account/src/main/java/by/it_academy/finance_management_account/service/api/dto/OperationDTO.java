package by.it_academy.finance_management_account.service.api.dto;

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
public class OperationDTO {
    private UUID operationUuid;
    private Instant dt_create;
    private Instant dt_update;
    private Instant data;
    private String description;
    private UUID category;
    private Double value;
    private UUID currency;
}
