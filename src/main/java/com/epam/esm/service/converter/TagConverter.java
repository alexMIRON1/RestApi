package com.epam.esm.service.converter;

import com.epam.esm.model.entity.Tag;
import com.epam.esm.service.dto.TagModel;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("tagConverter")
@Slf4j
public class TagConverter implements Converter<Tag, TagModel>{
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public TagModel convertToModel(Tag entity) {
        if(entity!=null){
            TagModel model = modelMapper.map(entity,TagModel.class);
            log.info( model.getId() + "tag entity was successfully converted to model");
            return model;
        }
        log.debug("tag entity was not converted to model -> entity is null");
        return null;
    }

    @Override
    public Tag convertToEntity(TagModel model) {
        if(model!=null){
            Tag entity = modelMapper.map(model,Tag.class);
            log.info(entity.getId() + "tag model was successfully converted to entity");
            return entity;
        }
        log.debug("tag model was not converted to entity -> model is null");
        return null;
    }
}
