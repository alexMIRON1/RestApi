package com.epam.esm.service.logic;

import com.epam.esm.service.dto.GiftCertificateModel;
import com.epam.esm.service.dto.TagModel;

import java.util.List;
import java.util.Map;

/**
 * Interface {@code GiftCertificateService} describes abstract behavior for working with {@link GiftCertificateModel} objects.
 * @author Oleksandr Myronenko
 */
public interface GiftCertificateService extends CRUDService<GiftCertificateModel>{
    /**
     * Method for getting map where key is list of certificate's models, value is list of tag's models by specific description.
     * @param description description of certificate's entity to get
     * @return map where key is list of certificate's models, value is list of tag's models
     */
    Map<List<GiftCertificateModel>,List<TagModel>> getCertificatesWithTagsByPartOfDescription(String description);

    /**
     * Method for getting map where key is list of certificate's models, value is list of tag's models by create date asc
     * @return map where key is list of certificate's models, value is list of tag's models
     */
    Map<List<GiftCertificateModel>,List<TagModel>>  getCertificatesWithTagsSortByCreateDateASC();
}
