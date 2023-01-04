package com.epam.esm.model.dao;
/**
 * Interface {@code CRUDDao} describes CRUD operations for working with database tables.
 *
 * @param <T> the type parameter
 * @author Oleksandr Myronenko
 */
public interface CRUDDao<T> extends CRDDao<T>{
    /**
     * Method for updating an entity in a table
     * @param item entity object to update
     */
    void update(T item);
}
