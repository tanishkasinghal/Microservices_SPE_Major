package com.example.LeaveApplicationService.services;

import com.example.LeaveApplicationService.entities.Department;

import java.util.List;

public interface DepartmentService {
    Department addDepartment(Department department);
    List<Department> getAllDepartments();
    Department getDepartmentById(String id);
}
