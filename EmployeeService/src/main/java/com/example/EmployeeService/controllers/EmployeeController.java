package com.example.EmployeeService.controllers;

import com.example.EmployeeService.config.AppConstants;
import com.example.EmployeeService.entity.Employee;
import com.example.EmployeeService.payload.ApiResponse;
import com.example.EmployeeService.payload.EmployeeResponse;
import com.example.EmployeeService.services.EmployeeService;
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
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    private static final Logger logger = LogManager.getLogger(EmployeeController.class);
    @PostMapping("/")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee){
        Employee newEmployee=this.employeeService.addEmployee(employee);
        logger.info("Adding Employee");
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<Employee> registerUser(@RequestBody Employee employee){
        Employee newUser=this.employeeService.registerNewUser(employee);
        logger.info("Registering Employee");
        return new ResponseEntity<Employee>(newUser,HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<EmployeeResponse> getAllEmployees(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
                                                            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir){
        EmployeeResponse employeeResponse=this.employeeService.getAllEmployee(pageNumber,pageSize,sortBy,sortDir);
        logger.info("Getting All Employee");
        return new ResponseEntity<EmployeeResponse>(employeeResponse,HttpStatus.OK);
    }

    @GetMapping("/department/{deptId}/")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(@PathVariable String deptId){
        List<Employee> employees=this.employeeService.getEmployeeByDepartment(deptId);
        logger.info("Getting Department By id");
        return new ResponseEntity<List<Employee>>(employees,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee,@PathVariable String id){
        Employee updatedEmployeeDTO=this.employeeService.updateEmployeeDetails(employee,id);
        logger.info("Update Employee");
        return ResponseEntity.ok(updatedEmployeeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable("id") String id){
        this.employeeService.deleteEmployee(id);
        ApiResponse response=ApiResponse.builder().message("User Deleted Successfully").success(true).status(HttpStatus.OK).build();
        logger.info("Deleting Employee");
        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeebyId(@PathVariable String id){
        Employee employee=this.employeeService.getEmployeeById(id);
        logger.info("Getting Employee by Id");
        return new ResponseEntity<Employee>(employee,HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Employee>> searchEmployeeByName(
            @PathVariable("keyword") String keyword){
        List<Employee> employee=this.employeeService.serachEmployee(keyword);
        logger.info("Searching Employee");
        return new ResponseEntity<List<Employee>>(employee,HttpStatus.OK);
    }
}
