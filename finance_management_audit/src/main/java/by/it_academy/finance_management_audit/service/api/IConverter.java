package by.it_academy.finance_management_audit.service.api;

public interface IConverter<S, T> {

    public T toEntity(S source);
}
