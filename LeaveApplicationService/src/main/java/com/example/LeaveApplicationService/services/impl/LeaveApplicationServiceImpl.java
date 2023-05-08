package com.example.LeaveApplicationService.services.impl;

import com.example.LeaveApplicationService.config.AppConstants;
import com.example.LeaveApplicationService.entities.Department;
import com.example.LeaveApplicationService.entities.Employee;
import com.example.LeaveApplicationService.entities.LeaveApplication;
import com.example.LeaveApplicationService.exceptions.ResourceNotFoundException;
import com.example.LeaveApplicationService.repositories.LeaveApplicationRepository;
import com.example.LeaveApplicationService.services.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {
    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public LeaveApplication addapplication(LeaveApplication leaveApplication) {
        String randomLeaveId= UUID.randomUUID().toString();
        leaveApplication.setApplicationId(randomLeaveId);
        leaveApplication.setApplicationDate(new Date());
        leaveApplication.setLeaveStatus(AppConstants.Application_PENDING);
        return this.leaveApplicationRepository.save(leaveApplication);
    }

    @Override
    public LeaveApplication submitResponse(LeaveApplication leaveApplication) {
        LeaveApplication leaveApplication1=this.leaveApplicationRepository.findById(leaveApplication.getApplicationId()).orElseThrow(()-> new ResourceNotFoundException("App;ication with given id not found"+leaveApplication.getApplicationId()));
        leaveApplication1.setLeaveType(leaveApplication.getLeaveType());
        leaveApplication1.setMessage(leaveApplication.getMessage());
        leaveApplication1.setRemarks(leaveApplication.getRemarks());
        leaveApplication1.setDateOfApproval(new Date());
        leaveApplication1.setLeaveStatus(leaveApplication.getLeaveStatus());
        leaveApplication1.setApplicationDate(leaveApplication.getApplicationDate());
        leaveApplication1.setEmployeeId(leaveApplication.getEmployeeId());
        return this.leaveApplicationRepository.save(leaveApplication1);
    }
    @Override
    public List<LeaveApplication> getAllApplication() {
        List<LeaveApplication> applications= this.leaveApplicationRepository.findAll();
         return applications;
    }

//
    @Override
    public List<LeaveApplication> getAllPendingApplication() {
        List<LeaveApplication> pendingApplications=this.leaveApplicationRepository.findAllByLeaveStatus(AppConstants.Application_PENDING);
        return pendingApplications;
    }

    @Override
    public List<LeaveApplication> getAllApprovedApplication() {
        List<LeaveApplication> pendingApplications=this.leaveApplicationRepository.findAllByLeaveStatus(AppConstants.Application_APPROVED);
        return pendingApplications;
    }

    @Override
    public List<LeaveApplication> getAllRejectedApplication() {
        List<LeaveApplication> pendingApplications=this.leaveApplicationRepository.findAllByLeaveStatus(AppConstants.Application_REJECTED);
        return pendingApplications;
    }




}
