package org.antop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "LOCATIONS")
public class Location {
    @Id
    @Column(name = "LOCATION_ID")
    private Integer id;
    @Column(name = "STREET_ADDRESS")
    private String streetAddress;
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE_PROVINCE")
    private String stateProvince;
    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;
}
