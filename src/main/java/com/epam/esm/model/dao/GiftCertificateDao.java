package com.epam.esm.model.dao;

import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.model.entity.Tag;

import java.util.List;
import java.util.Map;

/**
 * Interface {@code GiftCertificateDao} describes giftCertificateDao operations for working with database tables.
 * @author Oleksandr Myronenko
 */
public interface GiftCertificateDao extends CRUDDao<GiftCertificate>{
    /**
     * Method for getting map where key is list of certificates, value is list of tags from table with specific description
     * @param description description of certificates to get
     * @return map where key is list of certificates, values is list of tags
     */
    Map<List<GiftCertificate>,List<Tag>> getCertificatesWithTagsByPartOfDescription(String description);

    /**
     * Method for getting map where key is list of certificates, values is list of tags from table sort by create date asc
     * @return map where key is list of certificates, values is list of tags
     */
    Map<List<GiftCertificate>, List<Tag>> getCertificatesWithTagsSortByCreateDateASC();
}
