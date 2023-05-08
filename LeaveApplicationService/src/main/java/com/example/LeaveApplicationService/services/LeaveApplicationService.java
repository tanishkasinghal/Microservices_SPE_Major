package com.example.LeaveApplicationService.services;

import com.example.LeaveApplicationService.entities.Department;
import com.example.LeaveApplicationService.entities.LeaveApplication;

import java.util.List;

public interface LeaveApplicationService {
//    Department addDepartment(Department department);
//    List<Department> getAllDepartments();
//    Department getDepartmentById(String id);

    LeaveApplication addapplication(LeaveApplication leaveApplication);
    List<LeaveApplication> getAllApplication();

    List<LeaveApplication> getAllPendingApplication();
    List<LeaveApplication> getAllApprovedApplication();
    List<LeaveApplication> getAllRejectedApplication();


    LeaveApplication submitResponse(LeaveApplication leaveApplication);
}
