package com.epam.esm.model.dao;

import java.util.List;

/**
 * Interface {@code CRDDao} describes CRD operations for working with database tables.
 *
 * @param <T> the type parameter
 * @author Oleksandr Myronenko
 */
public interface CRDDao<T> {
    /**
     * Method for getting an entity object from a table by ID
     * @param id ID of entity object
     * @return Entity object from table
     */
    T getById(Long id);

    /**
     * Method for getting all entities from a table.
     * @return list of entities in the table
     */
    List<T> getAll();

    /**
     * Method for saving an entity to a table
     * @param item entity for saving in table
     */
    void insert(T item);

    /**
     * Method for removing an entity from a table.
     * @param item entity to remove
     */
    void remove(T item);

}
