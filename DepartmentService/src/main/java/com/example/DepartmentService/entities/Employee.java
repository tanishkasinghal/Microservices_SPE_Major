package com.example.DepartmentService.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String EmpId;
    private String DeptId;
    private String firstName;
    private String lastName;
    private String emailId;
    private Date joiningDate;
    private String password;
}
