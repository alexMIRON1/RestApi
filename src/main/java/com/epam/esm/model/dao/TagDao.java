package com.epam.esm.model.dao;

import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.model.entity.Tag;

import java.util.List;
import java.util.Map;

/**
 * Interface {@code TagDao} describes tagDao operations for working with database tables.
 * @author Oleksandr Myronenko
 */

public interface TagDao extends CRDDao<Tag>{
    /**
     * Method for getting map where key is list of certificates, value is list of tags from table by specific tag name
     * @param name name of tags to get
     * @return map where key is list of certificates, values is list of tags
     */
    Map<List<GiftCertificate>, List<Tag>> getCertificatesWithTags(String name);
}
