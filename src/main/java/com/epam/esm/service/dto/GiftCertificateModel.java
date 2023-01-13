package com.epam.esm.service.dto;

import com.epam.esm.service.deserializers.GiftCertificatePeriodDeserializer;
import com.epam.esm.service.serializers.GiftCertificateInstantSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.Period;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
public class GiftCertificateModel {
    private Long id;
    private Set<TagModel> tags = new HashSet<>();

    private String name;

    private String description;

    private Double price;
    @JsonDeserialize(using = GiftCertificatePeriodDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Period duration;

    @JsonSerialize(using = GiftCertificateInstantSerializer.class)
    private Instant createDate;
    @JsonSerialize(using = GiftCertificateInstantSerializer.class)
    private Instant lastUpdateDate;
    public void addTag(TagModel tag){
        this.tags.add(tag);
        tag.getCertificates().add(this);
    }
    public void removeTag(Long tagId){
        TagModel tag = this.tags.stream().filter(t->Objects.equals(t.getId(), tagId)).findFirst().orElse(null);
        if(tag!=null){
            this.tags.remove(tag);
            tag.getCertificates().remove(this);
        }
    }

}
