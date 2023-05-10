package com.example.LeaveApplicationService.controllers;

import com.example.LeaveApplicationService.entities.Department;
import com.example.LeaveApplicationService.entities.LeaveApplication;
import com.example.LeaveApplicationService.services.LeaveApplicationService;
import jakarta.validation.Valid;
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

    @PostMapping("/")
    public ResponseEntity<LeaveApplication> applyLeave(@Valid @RequestBody LeaveApplication leaveApplication){
        LeaveApplication newApplication=this.leaveApplicationService.addapplication(leaveApplication);
        return new ResponseEntity<>(newApplication, HttpStatus.CREATED);
    }
    @PostMapping("/submitResponse")
    public ResponseEntity<LeaveApplication> submitResponse(@Valid @RequestBody LeaveApplication leaveApplication){
        LeaveApplication newApplication=this.leaveApplicationService.submitResponse(leaveApplication);
        return new ResponseEntity<>(newApplication, HttpStatus.CREATED);

    }
    @GetMapping("/pending")
    public ResponseEntity<List<LeaveApplication>> getAllPendingApplication(){
        return ResponseEntity.ok(this.leaveApplicationService.getAllPendingApplication());
    }
    @GetMapping("/approved")
    public ResponseEntity<List<LeaveApplication>> getAllApprovedApplication(){
        return ResponseEntity.ok(this.leaveApplicationService.getAllApprovedApplication());
    }

    @GetMapping("/rejected")
    public ResponseEntity<List<LeaveApplication>> getAllRejectedApplication(){
        return ResponseEntity.ok(this.leaveApplicationService.getAllRejectedApplication());
    }

    @GetMapping("/")
    public ResponseEntity<List<LeaveApplication>> getAllApplication(){
        return ResponseEntity.ok(this.leaveApplicationService.getAllApplication());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<LeaveApplication>> getAllApplicationbyEmployeeId(@PathVariable String employeeId){
        return ResponseEntity.ok(this.leaveApplicationService.getAllApplicationbyEmployeeId(employeeId));
    }
}
