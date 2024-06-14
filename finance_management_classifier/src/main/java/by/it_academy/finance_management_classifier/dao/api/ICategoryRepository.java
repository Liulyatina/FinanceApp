package by.it_academy.finance_management_classifier.dao.api;

import by.it_academy.finance_management_classifier.dao.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, UUID> {
}
