package org.antop.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "REGIONS")
public class Region {
    @Id
    @Column(name = "REGION_ID")
    private Integer id;
    @Column(name = "REGION_NAME")
    private String name;
}
