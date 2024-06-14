package by.it_academy.finance_management_classifier.service.api.converter;

import by.it_academy.finance_management_classifier.dao.entity.CategoryEntity;
import by.it_academy.finance_management_classifier.service.api.IConverter;
import by.it_academy.finance_management_classifier.service.api.dto.CategoryDTO;

import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements IConverter<CategoryDTO, CategoryEntity> {

    @Override
    public CategoryEntity toEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        CategoryEntity operationCategoryEntity = new CategoryEntity();
        operationCategoryEntity.setUuid(categoryDTO.getUuid());
        operationCategoryEntity.setTitle(categoryDTO.getTitle());
        return operationCategoryEntity;
    }

    @Override
    public CategoryDTO toDTO(CategoryEntity categoryEntity) {
        if (categoryEntity == null) {
            return null;
        }
        CategoryDTO operationCategoryDTO = new CategoryDTO();
        operationCategoryDTO.setUuid(categoryEntity.getUuid());
        operationCategoryDTO.setTitle(categoryEntity.getTitle());
        return operationCategoryDTO;

    }
}
