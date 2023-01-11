package com.epam.esm.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class TagModel {
    private Long id;
    @JsonIgnore
    private Set<GiftCertificateModel> certificates = new HashSet<>();

    private String name;
}
