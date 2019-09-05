package org.antop.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity(name = "EMPLOYEES")
public class Employee {
    @Id
    @Column(name = "EMPLOYEE_ID")
    private int id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "HIRE_DATE")
    private LocalDate hireDate;
    @ManyToOne(targetEntity = Job.class)
    @JoinColumn(name = "JOB_ID")
    private Job job;
    @Column(name = "SALARY")
    private BigDecimal salary;
    @Column(name = "COMMISSION_PCT")
    private Double commissionPct;
    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "MANAGER_ID")
    private Employee manager;
    @ManyToOne(targetEntity = Department.class)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
}
