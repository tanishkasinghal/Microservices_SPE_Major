package com.example.DepartmentService.services.impl;

import com.example.DepartmentService.entities.Department;
import com.example.DepartmentService.entities.Employee;
import com.example.DepartmentService.repositories.DepartmentRepository;
import com.example.DepartmentService.services.DepartmentService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Department addDepartment(Department department) {
        String randomDepId= UUID.randomUUID().toString();
        department.setDeptId(randomDepId);
        return this.departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return this.departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(String id) {
        Department department= this.departmentRepository.findById(id).orElseThrow(()-> new ResolutionException("Department with given id not found"+id));
        //fetch employeelist
        //employeeby dept id use hoga

        ArrayList<Employee> employeesInDepartment=restTemplate.getForObject("http://localhost:8082/employee/department/"+department.getDeptId()+"/", ArrayList.class);
        department.setEmployeeList(employeesInDepartment);
        return department;
    }
}
