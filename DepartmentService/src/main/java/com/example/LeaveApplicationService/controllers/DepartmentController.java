package com.example.LeaveApplicationService.controllers;

import com.example.LeaveApplicationService.entities.Department;
import com.example.LeaveApplicationService.services.DepartmentService;
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
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    private static final Logger logger = LogManager.getLogger(DepartmentController.class);
    @PostMapping("/")
    public ResponseEntity<Department> addDepartment(@Valid @RequestBody Department department){
        Department newDepartment=this.departmentService.addDepartment(department);
        logger.info("Adding Department");
        return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentbyId(@PathVariable String id){
        logger.info("Getting Department by given id");
        return ResponseEntity.ok(this.departmentService.getDepartmentById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Department>> getAllDepartment(){
        logger.info("Getting list of all Departments");
        return ResponseEntity.ok(this.departmentService.getAllDepartments());
    }
}
