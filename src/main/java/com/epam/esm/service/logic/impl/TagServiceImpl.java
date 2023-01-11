package com.epam.esm.service.logic.impl;

import com.epam.esm.model.dao.TagDao;
import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.model.entity.Tag;
import com.epam.esm.service.converter.Converter;
import com.epam.esm.service.converter.GiftCertificateConverter;
import com.epam.esm.service.dto.GiftCertificateModel;
import com.epam.esm.service.dto.TagModel;
import com.epam.esm.service.logic.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;
    @Autowired
    @Qualifier("tagConverter")
    private Converter<Tag,TagModel> converter;

    @Override
    public TagModel getById(Long id) {
        TagModel tagModel = converter.convertToModel(tagDao.getById(id));
        log.info("successfully get by id tagModel "+ tagModel.getName() + " -> " + id);
        return tagModel;
    }

    @Override
    public List<TagModel> getAll() {
        List<Tag> tags = tagDao.getAll();
        log.info("successfully get all tags " + tags);
        return tags.stream().map(tag -> converter.convertToModel(tag)).collect(Collectors.toList());
    }

    @Override
    public void insert(TagModel entity) {
        tagDao.insert(converter.convertToEntity(entity));
        log.info("successfully saved tagModel " + entity.getName());
    }


    @Override
    public void remove(TagModel item) {
        tagDao.remove(converter.convertToEntity(item));
        log.info("successfully removed tagModel" + item.getName());
    }

    @Override
    public Map<List<GiftCertificate>, List<TagModel>> getCertificatesWithTags(String name) {
        Map<List<GiftCertificate>, List<Tag>> entitiesResult = tagDao.getCertificatesWithTags(name);
        log.info("successfully get map of certificates and tags " + entitiesResult +
                " by specific tag's name -> " + name);
        return entitiesResult.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, v->v.getValue().stream()
                        .map(tag -> converter.convertToModel(tag)).collect(Collectors.toList())));
    }
}
