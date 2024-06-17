package by.it_academy.finance_management_classifier.controller.http;

import by.it_academy.finance_management_classifier.dao.entity.CurrencyEntity;
import by.it_academy.finance_management_classifier.service.api.converter.CurrencyConverter;
import by.it_academy.finance_management_classifier.service.api.dto.CurrencyDTO;
import by.it_academy.finance_management_classifier.service.api.dto.PageOfClassifierDTO;
import by.it_academy.finance_management_classifier.service.impl.CurrencyImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classifier/currency")
public class CurrencyController {
    private final CurrencyImpl currencyService;
    private final CurrencyConverter currencyConverter;

    public CurrencyController(CurrencyImpl currencyService, CurrencyConverter currencyConverter) {
        this.currencyService = currencyService;
        this.currencyConverter = currencyConverter;
    }

    @GetMapping
    public ResponseEntity<PageOfClassifierDTO> getAllCurrencies(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                              @RequestParam(value = "size", defaultValue = "20") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        PageOfClassifierDTO pageOfCategoryDTO = currencyService.getAll(pageable);
        return ResponseEntity.ok(pageOfCategoryDTO);
    }

    @PostMapping
    public ResponseEntity<CurrencyDTO> createCurrency(@RequestBody CurrencyDTO currencyDTO) {
        CurrencyEntity entity = currencyConverter.toEntity(currencyDTO);
        CurrencyEntity createdEntity = currencyService.create(entity);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}