package com.epam.esm.service.dto;

import com.epam.esm.service.deserializers.GiftCertificateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class GiftCertificateModel {
    private Long id;
    private Set<TagModel> tags = new HashSet<>();

    private String name;

    private String description;

    private Double price;
    @JsonDeserialize(using = GiftCertificateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Period duration;

    private Instant createDate;

    private Instant lastUpdateDate;
    public void addTag(TagModel tag){
        this.tags.add(tag);
        tag.getCertificates().add(this);
    }
    public void removeTag(TagModel tag){
//        tags.forEach(tagModel -> System.out.println(tagModel.getName()));
        this.tags.remove(tag);
        tag.getCertificates().remove(this);
//        tags.forEach(tagModel -> System.out.println(tagModel.getName()));
    }
}
