package by.it_academy.finance_management_user.service.converter;

import by.it_academy.finance_management_user.core.dto.MailDTO;
import by.it_academy.finance_management_user.dao.entity.MailEntity;
import by.it_academy.finance_management_user.dao.entity.UserEntity;
import by.it_academy.finance_management_user.service.api.IConverter;
import org.springframework.stereotype.Component;

@Component
public class MailConverter implements IConverter<MailDTO, MailEntity> {

    @Override
    public MailDTO toDTO(MailEntity entity) {
        if (entity == null) {
            return null;
        }
        String email = entity.getMail() != null ? entity.getMail().getMail() : null;

        return new MailDTO(
                entity.getUuid(),
                email,
                entity.getStatus()
        );
    }

    @Override
    public MailEntity toEntity(MailDTO dto) {
        if (dto == null) {
            return null;
        }
        MailEntity entity = new MailEntity();
        entity.setUuid(dto.getUuid());
        entity.setStatus(dto.getStatus());

        if (dto.getEmail() != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setMail(dto.getEmail());
            entity.setMail(userEntity);
        }

        return entity;
    }
}