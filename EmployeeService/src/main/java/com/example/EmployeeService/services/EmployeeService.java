package com.example.EmployeeService.services;

import com.example.EmployeeService.entity.Employee;
import com.example.EmployeeService.payload.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    Employee registerNewUser(Employee employee);
    Employee addEmployee(Employee employee);
    Employee updateEmployeeDetails(Employee employee,String id);
    Employee getEmployeeById(String id);
    EmployeeResponse getAllEmployee(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    void deleteEmployee(String id);

     List<Employee> getEmployeeByDepartment(String departmentId);
    List<Employee> serachEmployee(String name);
}
