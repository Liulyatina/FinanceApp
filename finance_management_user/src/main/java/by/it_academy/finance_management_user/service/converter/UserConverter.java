package by.it_academy.finance_management_user.service.converter;

import by.it_academy.finance_management_user.dao.entity.UserEntity;
import by.it_academy.finance_management_user.service.api.IConverter;
import by.it_academy.finance_management_user.core.dto.UserDTO;
import by.it_academy.finance_management_user.core.dto.UserRegistrationDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements IConverter<UserDTO, UserEntity> {

    @Override
    public UserEntity toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUuid(userDTO.getUuid());
        userEntity.setDt_create(userDTO.getDt_create());
        userEntity.setDt_update(userDTO.getDt_update());
        userEntity.setMail(userDTO.getMail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setFio(userDTO.getFio());
        userEntity.setRole(userDTO.getRole());
        userEntity.setStatus(userDTO.getStatus());
        return userEntity;
    }
    public UserEntity toEntity(UserRegistrationDTO registrationDto) {
        if (registrationDto == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setMail(registrationDto.getMail());
        userEntity.setFio(registrationDto.getFio());
        userEntity.setPassword(registrationDto.getPassword());
        return userEntity;
    }
    @Override
    public UserDTO toDTO(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid(userEntity.getUuid());
        userDTO.setDt_create(userEntity.getDt_create());
        userDTO.setDt_update(userEntity.getDt_update());
        userDTO.setMail(userEntity.getMail());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setFio(userEntity.getFio());
        userDTO.setRole(userEntity.getRole());
        userDTO.setStatus(userEntity.getStatus());
        return userDTO;
    }

}
