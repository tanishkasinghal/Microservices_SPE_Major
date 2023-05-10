package com.example.EmployeeService.repository;

import com.example.EmployeeService.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer > {
}
