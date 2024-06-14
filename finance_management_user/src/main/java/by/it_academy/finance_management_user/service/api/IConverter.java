package by.it_academy.finance_management_user.service.api;

/**
 * Интерфейс для конвертации между двумя типами данных.
 *
 * @param <S> Исходный тип данных (например, DTO).
 * @param <T> Целевой тип данных (например, сущность базы данных).
 */
public interface IConverter<S, T> {

    /**
     * Преобразует данные из типа S в тип T.
     *
     * @param source Исходный объект.
     * @return Преобразованный объект типа T.
     */
    T toEntity(S source);

    /**
     * Преобразует данные из типа T в тип S.
     *
     * @param target Целевой объект.
     * @return Преобразованный объект типа S.
     */
    S toDTO(T target);
}