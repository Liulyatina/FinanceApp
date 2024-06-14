package by.it_academy.finance_management_classifier.controller.http;

import by.it_academy.finance_management_classifier.service.api.converter.CategoryConverter;
import by.it_academy.finance_management_classifier.service.api.dto.CategoryDTO;
import by.it_academy.finance_management_classifier.service.api.dto.PageOfClassifierDTO;
import by.it_academy.finance_management_classifier.service.impl.CategoryImpl;
import by.it_academy.finance_management_classifier.dao.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classifier/operation/category")
public class CategoryController {
    private final CategoryImpl operationCategory;
    private final CategoryConverter converter;

    public CategoryController(CategoryImpl operationCategory, CategoryConverter converter) {
        this.operationCategory = operationCategory;
        this.converter = converter;
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryDTO operationCategoryDTO){
        CategoryEntity categoryEntity = converter.toEntity(operationCategoryDTO);
        CategoryEntity createdCategory = operationCategory.create(categoryEntity);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<PageOfClassifierDTO> getAllCategory(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                              @RequestParam(value = "size", defaultValue = "20") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        PageOfClassifierDTO pageOfCategoryDTO = operationCategory.getAll(pageable);
        return ResponseEntity.ok(pageOfCategoryDTO);
    }
}
