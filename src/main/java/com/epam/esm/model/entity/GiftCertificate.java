package com.epam.esm.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Data
@Table(name = "gift_certificate")
public class GiftCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "certificate_tag",joinColumns = @JoinColumn(name = "certificate_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Double price;
    @Column(name = "duration")
    private Period duration;
    @Column(name = "create_date")
    private Instant createDate;
    @Column(name = "last_update_date")
    private Instant lastUpdateDate;
}
