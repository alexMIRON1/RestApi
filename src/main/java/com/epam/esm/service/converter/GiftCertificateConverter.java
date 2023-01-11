package com.epam.esm.service.converter;

import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.service.dto.GiftCertificateModel;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component("certificateConverter")
public class GiftCertificateConverter implements Converter<GiftCertificate,GiftCertificateModel>{

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public GiftCertificateModel convertToModel(GiftCertificate entity) {
        if(entity!=null){
            GiftCertificateModel certificateModel = modelMapper.map(entity,GiftCertificateModel.class);
            log.info("certificate entity was successfully converted to model");
            return certificateModel;
        }
        log.debug("certificate entity was not converted to model -> entity is null");
        return null;
    }

    @Override
    public GiftCertificate convertToEntity(GiftCertificateModel model) {
        if (model!=null){
            log.info("certificate model was successfully converted to entity");
            return modelMapper.map(model,GiftCertificate.class);
        }
        log.debug("certificate model was not converted to entity -> model is null");
        return null;
    }
}
