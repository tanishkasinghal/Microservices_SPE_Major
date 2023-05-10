package com.example.EmployeeService.payload;

import com.example.EmployeeService.entity.Employee;
import lombok.Data;

@Data
public class JwtAuthResponse {
    private String token;
    private Employee employee;
}
