package com.epam.esm.service.logic.impl;

import com.epam.esm.model.dao.GiftCertificateDao;
import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.model.entity.Tag;
import com.epam.esm.service.converter.Converter;
import com.epam.esm.service.converter.TagConverter;
import com.epam.esm.service.dto.GiftCertificateModel;
import com.epam.esm.service.dto.TagModel;
import com.epam.esm.service.logic.GiftCertificateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GiftCertificateServiceImpl implements GiftCertificateService {
    @Autowired
    private GiftCertificateDao giftCertificateDao;
    @Autowired
    @Qualifier("certificateConverter")
    private Converter<GiftCertificate,GiftCertificateModel> converter;
    @Autowired
    private TagConverter tagConverter;
    @Override
    public GiftCertificateModel getById(Long id) {
        GiftCertificateModel certificateModel = converter.convertToModel(giftCertificateDao.getById(id));
        log.info("get by id certificateModel " + certificateModel.getName() + " by id = " + id);
        return certificateModel;
    }

    @Override
    public List<GiftCertificateModel> getAll() {
        List<GiftCertificate> certificates = giftCertificateDao.getAll();
        log.info("get all certificates " + certificates );
        return certificates.stream().map(giftCertificate -> converter.convertToModel(giftCertificate))
                .collect(Collectors.toList());
    }

    @Override
    public void insert(GiftCertificateModel model) {
        model.setCreateDate(Instant.now());
        model.setLastUpdateDate(Instant.now());
        giftCertificateDao.insert(converter.convertToEntity(model));
        log.info("saved certificateModel " + model.getName());
    }

    @Override
    public void remove(GiftCertificateModel item) {
        giftCertificateDao.remove(converter.convertToEntity(item));
        log.info("removed certificateModel " + item.getName());
    }

    @Override
    public void update(GiftCertificateModel model) {
        model.setLastUpdateDate(Instant.now());
        giftCertificateDao.update(converter.convertToEntity(model));
        log.info("updated certificateModel " + model.getName());
    }

    @Override
    public Map<List<GiftCertificateModel>, List<TagModel>> getCertificatesWithTagsByPartOfDescription(String description) {
        Map<List<GiftCertificate>,List<Tag>> entitiesResult = giftCertificateDao
                .getCertificatesWithTagsByPartOfDescription(description);
        log.info("get map of certificates and tags " + entitiesResult +
                 " by specific certificate's description -> " + description);
        return entitiesResult.entrySet().stream()
                .collect(Collectors.toMap(k->k.getKey().stream()
                        .map(giftCertificate -> converter.convertToModel(giftCertificate))
                        .collect(Collectors.toList()), v->v.getValue().stream()
                        .map(tag -> tagConverter.convertToModel(tag)).collect(Collectors.toList())));
    }

    @Override
    public Map<List<GiftCertificateModel>, List<TagModel>> getCertificatesWithTagsSortByCreateDateASC() {
        Map<List<GiftCertificate>,List<Tag>> entitiesResult =giftCertificateDao
                .getCertificatesWithTagsSortByCreateDateASC();
        log.info("get map of certificates and tags " + entitiesResult + " sorted by created date asc");
        return entitiesResult.entrySet().stream()
                .collect(Collectors.toMap(k->k.getKey().stream()
                        .map(giftCertificate -> converter.convertToModel(giftCertificate))
                        .collect(Collectors.toList()), v->v.getValue().stream()
                        .map(tag -> tagConverter.convertToModel(tag)).collect(Collectors.toList())));
    }
}
