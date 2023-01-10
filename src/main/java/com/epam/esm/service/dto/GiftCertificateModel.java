package com.epam.esm.service.dto;

import lombok.Data;

import java.time.Instant;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Data
public class GiftCertificateModel {
    private Long id;

    private Set<TagModel> tags = new HashSet<>();

    private String name;

    private String description;

    private Double price;

    private Period duration;

    private Instant createDate;

    private Instant lastUpdate;
    public void addTag(TagModel tag){
        this.tags.add(tag);
        tag.getCertificates().add(this);
    }
    public void removeTag(TagModel tag){
        this.tags.remove(tag);
        tag.getCertificates().remove(this);
    }
}
