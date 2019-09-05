package org.antop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "DEPARTMENTS")
@Data
public class Department {
    @Id
    @Column(name = "DEPARTMENT_ID")
    private Integer id;
    @Column(name = "DEPARTMENT_NAME")
    private String name;
    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "MANAGER_ID")
    private Employee manager;
    @ManyToOne(targetEntity = Location.class)
    @JoinColumn(name = "LOCATION_ID")
    private Location location;
}
