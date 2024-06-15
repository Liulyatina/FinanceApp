package by.it_academy.finance_management_account.service.converter;

import by.it_academy.finance_management_account.dao.entity.OperationEntity;
import by.it_academy.finance_management_account.service.api.IConverter;
import by.it_academy.finance_management_account.core.dto.OperationDTO;
import org.springframework.stereotype.Component;

@Component
public class OperationConverter implements IConverter<OperationDTO, OperationEntity> {
    @Override
    public OperationEntity toEntity(OperationDTO operationDTO) {
        if (operationDTO == null) {
            return null;
        }
        OperationEntity operationEntity = new OperationEntity();

        operationEntity.setOperationUuid(operationDTO.getOperationUuid());
        operationEntity.setDescription(operationDTO.getDescription());
        operationEntity.setCategory(operationDTO.getCategory());
        operationEntity.setValue(operationDTO.getValue());
        operationEntity.setCurrency(operationDTO.getCurrency());
        operationEntity.setDate(operationDTO.getData());

        return operationEntity;
    }
    @Override
    public OperationDTO toDTO(OperationEntity operationEntity) {
        if (operationEntity == null) {
            return null;
        }
        OperationDTO operationDTO = new OperationDTO();

        operationDTO.setCategory(operationEntity.getCategory());
        operationDTO.setCurrency(operationEntity.getCurrency());
        operationDTO.setValue(operationEntity.getValue());
        operationDTO.setData(operationEntity.getDate());
        operationDTO.setOperationUuid(operationEntity.getOperationUuid());
        operationDTO.setDescription(operationEntity.getDescription());

        return operationDTO;
    }


}
