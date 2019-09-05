package org.antop.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "JOBS")
@Data
public class Job {
    @Id
    @Column(name = "JOB_ID")
    private String id;
    @Column(name = "JOB_TITLE")
    private String title;
    @Column(name = "MIN_SALARY")
    private BigDecimal minSalary;
    @Column(name = "MAX_SALARY")
    private BigDecimal maxSalary;
}
