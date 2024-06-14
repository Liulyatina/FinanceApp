package by.it_academy.finance_management_user.service.api;

import by.it_academy.finance_management_user.core.dto.UserDTO;
import by.it_academy.finance_management_user.core.dto.UserLoginDTO;
import by.it_academy.finance_management_user.core.dto.UserRegistrationDTO;

public interface IRegistrationService {

    void registration(UserRegistrationDTO registrationDTO);
    void verify(String code, String mail);
    String login(UserLoginDTO userLoginDTO);
    UserDTO getInfo(String token);
}
