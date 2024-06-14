package by.it_academy.finance_management_classifier.service.feign.dto;

import by.it_academy.finance_management_classifier.service.feign.enums.UserRole;
import by.it_academy.finance_management_classifier.service.feign.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private UUID uuid;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String mail;

    @NotBlank(message = "fio is mandatory")
    private String fio;

    private UserRole role;

    private UserStatus status;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
