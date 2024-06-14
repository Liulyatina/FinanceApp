package by.it_academy.finance_management_account.service.api;

public interface IConverter<S, T> {

    public T toEntity(S source);

    public S toDTO(T target);
}
