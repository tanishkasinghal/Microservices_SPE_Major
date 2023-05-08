package com.example.LeaveApplicationService.repositories;

import com.example.LeaveApplicationService.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,String> {

}
