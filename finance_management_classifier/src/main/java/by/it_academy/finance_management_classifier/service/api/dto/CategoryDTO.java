package by.it_academy.finance_management_classifier.service.api.dto;

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
public class CategoryDTO {
    private UUID uuid;
    private Instant dt_create;
    private Instant dt_update;
    private String title;

}
