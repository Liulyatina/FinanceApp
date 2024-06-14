package by.it_academy.finance_management_user.core.dto;

import by.it_academy.finance_management_user.core.enums.MailStatus;
import lombok.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MailDTO {
    private UUID uuid;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    private MailStatus status;

}