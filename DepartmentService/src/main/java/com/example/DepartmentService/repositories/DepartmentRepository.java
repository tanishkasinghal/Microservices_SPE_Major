package com.example.DepartmentService.repositories;

import com.example.DepartmentService.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,String> {

}
