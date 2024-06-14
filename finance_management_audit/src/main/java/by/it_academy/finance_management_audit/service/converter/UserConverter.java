package by.it_academy.finance_management_audit.service.converter;

import by.it_academy.finance_management_audit.dao.entity.UserEntity;
import by.it_academy.finance_management_audit.service.api.IConverter;
import by.it_academy.finance_management_audit.core.dto.UserAuditDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements IConverter<UserAuditDTO, UserEntity> {

    @Override
    public UserEntity toEntity(UserAuditDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setUuid(userDTO.getUuid());
        user.setMail(userDTO.getMail());
        user.setFio(userDTO.getFio());
        user.setRole(userDTO.getRole());
        return user;
    }

    @Override
    public UserAuditDTO toDTO(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UserAuditDTO userDto = new UserAuditDTO();
        userDto.setUuid(userEntity.getUuid());
        userDto.setMail(userEntity.getMail());
        userDto.setFio(userEntity.getFio());
        userDto.setRole(userEntity.getRole());
        return userDto;
    }
}
