package com.epam.esm.model.dao;

import com.epam.esm.model.entity.Tag;

import java.util.List;

public interface TagDao extends CRDDao<Tag>{
    /**
     * Method for getting list of tags from a table with specific name
     * @param name name of tags to get
     * @return List of tags from table
     */
    List<Tag> getByName(String name);
}
