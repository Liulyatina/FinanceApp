package by.it_academy.finance_management_classifier.service.impl;

import by.it_academy.finance_management_classifier.dao.api.ICategoryRepository;
import by.it_academy.finance_management_classifier.dao.entity.CategoryEntity;
import by.it_academy.finance_management_classifier.service.api.IClassifierService;
import by.it_academy.finance_management_classifier.service.api.converter.CategoryConverter;
import by.it_academy.finance_management_classifier.service.api.dto.PageOfClassifierDTO;
import by.it_academy.finance_management_classifier.service.feign.api.AuditClientFeign;
import by.it_academy.finance_management_classifier.service.feign.dto.AuditCreateDTO;
import by.it_academy.finance_management_classifier.service.feign.enums.EssenceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CategoryImpl implements IClassifierService<CategoryEntity> {
    private final ICategoryRepository categoryRepository;
    private final CategoryConverter converter;
    private final AuditClientFeign auditClient;


    public CategoryImpl(ICategoryRepository categoryRepository, CategoryConverter converter, AuditClientFeign auditClient) {
        this.categoryRepository = categoryRepository;
        this.converter = converter;
        this.auditClient = auditClient;
    }

    @Override
    @Transactional
    public CategoryEntity create(CategoryEntity categoryEntity) {
        categoryEntity.setUuid(UUID.randomUUID());
        categoryEntity = categoryRepository.saveAndFlush(categoryEntity);

        AuditCreateDTO auditCreateDTO = AuditCreateDTO.builder()
                .type(EssenceType.CATEGORY)
                .uuidUser(null)
                .uuidEntity(categoryEntity.getUuid())
                .text("Category created successfully")
                .build();

        auditClient.createAuditAction(auditCreateDTO);

        return categoryEntity;
    }

    @Override
    public PageOfClassifierDTO getAll(Pageable pageable) {
        Page<CategoryEntity> categoryEntities = categoryRepository.findAll(pageable);
        List<Object> categoryDTOs = categoryEntities.getContent().stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
        return PageOfClassifierDTO.builder()
                .number(categoryEntities.getNumber())
                .size(categoryEntities.getSize())
                .totalPages(categoryEntities.getTotalPages())
                .totalElements(categoryEntities.getTotalElements())
                .first(categoryEntities.isFirst())
                .numberOfElements(categoryEntities.getNumberOfElements())
                .last(categoryEntities.isLast())
                .content(categoryDTOs)
                .build();
    }
}