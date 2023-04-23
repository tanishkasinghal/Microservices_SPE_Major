package com.example.DepartmentService.controllers;

import com.example.DepartmentService.entities.Department;
import com.example.DepartmentService.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public ResponseEntity<Department> addDepartment(@Valid @RequestBody Department department){
        Department newDepartment=this.departmentService.addDepartment(department);
        return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentbyId(@PathVariable String id){
        return ResponseEntity.ok(this.departmentService.getDepartmentById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Department>> getAllDepartment(){
        System.out.println("jn");
        return ResponseEntity.ok(this.departmentService.getAllDepartments());
    }
}
