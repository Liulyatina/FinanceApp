package by.it_academy.finance_management_classifier.service.api.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PageOfClassifierDTO<T> {

    private Integer number;

    private Integer size;

    private Integer totalPages;

    private Long totalElements;

    private Boolean first;

    private Integer numberOfElements;

    private Boolean last;

    private List<T> content;

}
