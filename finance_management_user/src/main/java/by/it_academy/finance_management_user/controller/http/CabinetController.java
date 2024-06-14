package by.it_academy.finance_management_user.controller.http;

import by.it_academy.finance_management_user.core.dto.UserDTO;
import by.it_academy.finance_management_user.core.dto.UserLoginDTO;
import by.it_academy.finance_management_user.core.dto.UserRegistrationDTO;
import by.it_academy.finance_management_user.service.impl.RegistrationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cabinet", produces = "application/json; charset=UTF-8")
public class CabinetController {
    private final RegistrationServiceImpl registrationUserService;

    public CabinetController(RegistrationServiceImpl registrationService) {
        this.registrationUserService = registrationService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody UserRegistrationDTO registrationDTO) {
        registrationUserService.registration(registrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/verification")
    public ResponseEntity<String> verifyUser(@RequestParam("code") String code, @RequestParam("mail") String mail) {
        registrationUserService.verify(code, mail);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        String token = registrationUserService.login(userLoginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(token);

    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserInfo(@RequestHeader("Authorization") String token) {
        UserDTO userDTO = registrationUserService.getInfo(token);
        return ResponseEntity.ok().body(userDTO);
    }
}
