package home.hw.service;

import home.hw.exception.InvalidDataEntityException;

import java.io.Serializable;
import java.util.List;

public interface Crudable<T, ID extends Serializable> {
    ID create(T entity) throws InvalidDataEntityException;

    void update(T entity);

    void delete(T entity);

    T getById(ID id);

    List<T> getAll();

    void isValidEntity(T entity) throws InvalidDataEntityException;
}
