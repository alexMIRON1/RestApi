package com.epam.esm.service.converter;

/**
 * Interface {@code Converter} convert entity to model and vice versa
 * @param <T> the type parameter of  entity
 * @param <Y> the type parameter of model
 * @author Oleksandr Myronenko
 */
public interface Converter <T,Y>{
     Y convertToModel(T entity);

     T convertToEntity(Y model);
}
