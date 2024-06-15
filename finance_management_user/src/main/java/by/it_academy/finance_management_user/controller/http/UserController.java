package by.it_academy.finance_management_user.controller.http;

import by.it_academy.finance_management_user.core.dto.PageOfUserDTO;
import by.it_academy.finance_management_user.core.dto.UserDTO;
import by.it_academy.finance_management_user.service.converter.UserConverter;
import by.it_academy.finance_management_user.service.impl.UserServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users", produces = "application/json; charset=UTF-8")
public class UserController {

    private final UserServiceImpl userService;
    private final UserConverter userConverter;

    public UserController(UserServiceImpl userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto) {
        userService.create(userDto);
        return ResponseEntity.status(201).build();
    }
    @GetMapping
    public ResponseEntity<PageOfUserDTO> getAllUsers(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "size", defaultValue = "20") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        PageOfUserDTO pageOfUserDTO = userService.getAll(pageable);
        return ResponseEntity.ok(pageOfUserDTO);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("uuid") UUID uuid) {
        UserDTO user = userConverter.toDTO(userService.getById(uuid));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("uuid") UUID uuid,
                                              @PathVariable("dt_update") Instant dt_update,
                                              @RequestBody UserDTO userDto) {
        UserDTO updateUser = userConverter.toDTO(userService.update(uuid, dt_update, userDto));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteUser(@PathVariable("uuid") UUID uuid) {
        userService.delete(uuid);
        return ResponseEntity.noContent().build();
    }

}
