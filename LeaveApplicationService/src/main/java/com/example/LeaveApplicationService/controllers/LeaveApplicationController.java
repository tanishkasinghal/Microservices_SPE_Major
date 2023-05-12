package com.example.LeaveApplicationService.controllers;

import com.example.LeaveApplicationService.entities.Department;
import com.example.LeaveApplicationService.entities.LeaveApplication;
import com.example.LeaveApplicationService.services.LeaveApplicationService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/leave")
public class LeaveApplicationController {
    @Autowired
    private LeaveApplicationService leaveApplicationService;
    private static final Logger logger = LogManager.getLogger(LeaveApplicationController.class);
    @PostMapping("/")
    public ResponseEntity<LeaveApplication> applyLeave(@Valid @RequestBody LeaveApplication leaveApplication){
        LeaveApplication newApplication=this.leaveApplicationService.addapplication(leaveApplication);
        logger.info("Applying Leave");
        return new ResponseEntity<>(newApplication, HttpStatus.CREATED);
    }
    @PostMapping("/submitResponse")
    public ResponseEntity<LeaveApplication> submitResponse(@Valid @RequestBody LeaveApplication leaveApplication){
        LeaveApplication newApplication=this.leaveApplicationService.submitResponse(leaveApplication);
        logger.info("Submitting Leave APplication Response");
        return new ResponseEntity<>(newApplication, HttpStatus.CREATED);

    }
    @GetMapping("/pending")
    public ResponseEntity<List<LeaveApplication>> getAllPendingApplication(){
        logger.info("Getting All Pending Application");
        return ResponseEntity.ok(this.leaveApplicationService.getAllPendingApplication());
    }
    @GetMapping("/approved")
    public ResponseEntity<List<LeaveApplication>> getAllApprovedApplication(){
        logger.info("Approving Leave Application");
        return ResponseEntity.ok(this.leaveApplicationService.getAllApprovedApplication());
    }

    @GetMapping("/rejected")
    public ResponseEntity<List<LeaveApplication>> getAllRejectedApplication(){
        logger.info("Rejecting Leave Application");
        return ResponseEntity.ok(this.leaveApplicationService.getAllRejectedApplication());
    }

    @GetMapping("/")
    public ResponseEntity<List<LeaveApplication>> getAllApplication(){
        logger.info("Getting All Leave Application");
        return ResponseEntity.ok(this.leaveApplicationService.getAllApplication());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<LeaveApplication>> getAllApplicationbyEmployeeId(@PathVariable String employeeId){
        logger.info("Getting Application by Employee Id");
        return ResponseEntity.ok(this.leaveApplicationService.getAllApplicationbyEmployeeId(employeeId));
    }
}
