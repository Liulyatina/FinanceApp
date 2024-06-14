package by.it_academy.finance_management_classifier.service.api.converter;

import by.it_academy.finance_management_classifier.dao.entity.CurrencyEntity;
import by.it_academy.finance_management_classifier.service.api.IConverter;
import by.it_academy.finance_management_classifier.service.api.dto.CurrencyDTO;
import org.springframework.stereotype.Component;

@Component
public class CurrencyConverter implements IConverter<CurrencyDTO, CurrencyEntity> {
    @Override
    public CurrencyEntity toEntity(CurrencyDTO source) {
        if (source == null) {
            return null;
        }
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setUuid(source.getUuid());
        currencyEntity.setTitle(source.getTitle());
        currencyEntity.setDescription(source.getDescription());
        return currencyEntity;
    }

    @Override
    public CurrencyDTO toDTO(CurrencyEntity target) {
        if (target == null) {
            return null;
        }
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setUuid(target.getUuid());
        currencyDTO.setTitle(target.getTitle());
        currencyDTO.setDescription(target.getDescription());
        return currencyDTO;
    }


}
