package by.it_academy.finance_management_classifier.service.api;

import by.it_academy.finance_management_classifier.service.api.dto.PageOfClassifierDTO;
import org.springframework.data.domain.Pageable;

public interface IClassifierService<T> {
    T create(T entity);
    PageOfClassifierDTO getAll(Pageable pageable);
}
