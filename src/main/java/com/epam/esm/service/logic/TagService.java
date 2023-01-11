package com.epam.esm.service.logic;

import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.service.dto.GiftCertificateModel;
import com.epam.esm.service.dto.TagModel;

import java.util.List;
import java.util.Map;


/**
 * Interface {@code TagService} describes abstract behavior for working with {@link TagModel} objects.
 * @author Oleksandr Myronenko
 */
public interface TagService extends CRDService<TagModel>{
    /**
     * Method for getting map where key is list of certificate's models, value is list of tag's models by specific tag's name.
     * @param name name of tags to get
     * @return map where key is list of certificate's model, values is list of tag's model
     */
    Map<List<GiftCertificate>, List<TagModel>> getCertificatesWithTags(String name);
}
