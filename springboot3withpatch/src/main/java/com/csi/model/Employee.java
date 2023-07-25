package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue

    private int empId;

    @Size(min=2,message = "Name should be at least 2 char")
    private String empName;

    private String empAddress;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date empDOB;

    private double empSalary;

    @Column(unique = true)
    private long empContactNumber;

    @Email
    private String empEmailId;
}
