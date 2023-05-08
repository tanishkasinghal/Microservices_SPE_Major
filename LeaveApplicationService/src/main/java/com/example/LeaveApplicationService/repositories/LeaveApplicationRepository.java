package com.example.LeaveApplicationService.repositories;

import com.example.LeaveApplicationService.entities.Department;
import com.example.LeaveApplicationService.entities.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication,String> {
    List<LeaveApplication> findAllByLeaveStatus(Integer status);
}
