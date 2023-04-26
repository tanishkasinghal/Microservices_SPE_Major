package com.example.EmployeeService.services;

import com.example.EmployeeService.entity.Employee;
import com.example.EmployeeService.payload.EmployeeResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface EmployeeService {
    Employee registerNewUser(Employee employee);
    Employee addEmployee(Employee employee);
    Employee updateEmployeeDetails(Employee employee,String id);
    Employee getEmployeeById(String id);
    EmployeeResponse getAllEmployee(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    void deleteEmployee(String id);
    String generateToken(UserDetails userDetails);
    Boolean validateToken(String token, UserDetails userDetails);
    void validToken(String token);
     List<Employee> getEmployeeByDepartment(String departmentId);
    List<Employee> serachEmployee(String name);
}
