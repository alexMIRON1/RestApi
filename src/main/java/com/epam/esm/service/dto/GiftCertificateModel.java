package com.epam.esm.service.dto;

import com.epam.esm.model.entity.Tag;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class GiftCertificateModel {
    private Long id;

    private Set<Tag> tags = new HashSet<>();

    private String name;

    private String description;

    private Double price;

    private LocalDate duration;

    private Instant createDate;

    private Instant lastUpdate;
}
