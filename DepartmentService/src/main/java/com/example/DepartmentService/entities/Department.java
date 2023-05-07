package com.example.DepartmentService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="department")
public class Department {
    @Id
    private String deptId;
    private String deptName;
    @Transient
    private List<Employee> employeeList=new ArrayList<>();
}
