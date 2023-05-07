package com.example.DepartmentService.services;

import com.example.DepartmentService.entities.Department;

import java.util.List;

public interface DepartmentService {
    Department addDepartment(Department department);
    List<Department> getAllDepartments();
    Department getDepartmentById(String id);
}
