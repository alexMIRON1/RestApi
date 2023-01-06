package com.epam.esm.service.dto;

import com.epam.esm.model.entity.GiftCertificate;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TagModel {
    private Long id;

    private Set<GiftCertificate> certificates = new HashSet<>();

    private String name;
}
