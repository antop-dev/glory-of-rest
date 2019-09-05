package org.antop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "COUNTRIES")
public class Country {
    @Id
    @Column(name = "COUNTRY_ID")
    private String id;
    @Column(name = "COUNTRY_NAME")
    private String name;
    @ManyToOne(targetEntity = Region.class)
    @JoinColumn(name = "REGION_ID")
    private Region region;
}
