package com.epam.esm.service.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TagModel {
    private Long id;

    private Set<GiftCertificateModel> certificates = new HashSet<>();

    private String name;
}
