package com.example.EmployeeService.repository;

import com.example.EmployeeService.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,String> {


    List<Employee> findByDeptId(String DeptId);
    List<Employee> findByFirstNameContaining(String firstName);
    Optional<Employee> findByEmailId(String emailId);

}
