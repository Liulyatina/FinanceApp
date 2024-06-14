package by.it_academy.finance_management_user.service.converter;

import by.it_academy.finance_management_user.core.dto.VerificationDTO;
import by.it_academy.finance_management_user.dao.entity.UserEntity;
import by.it_academy.finance_management_user.dao.entity.VerificationEntity;
import by.it_academy.finance_management_user.service.api.IConverter;
import org.springframework.stereotype.Component;

@Component
public class VerificationConverter implements IConverter<VerificationDTO, VerificationEntity> {

    @Override
    public VerificationEntity toEntity(VerificationDTO dto) {
        if (dto == null) {
            return null;
        }

        VerificationEntity entity = new VerificationEntity();
        entity.setUuid(dto.getUuid());
        entity.setVerificationCode(dto.getVerificationCode());

        if (dto.getEmail() != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setMail(dto.getEmail());
            entity.setMail(userEntity);
        }

        return entity;
    }

    @Override
    public VerificationDTO toDTO(VerificationEntity entity) {
        if (entity == null) {
            return null;
        }

        String email = entity.getMail() != null ? entity.getMail().getMail() : null;

        return new VerificationDTO(
                entity.getUuid(),
                email,
                entity.getVerificationCode()
        );
    }
}