package com.example.LeaveApplicationService.services.impl;

import com.example.LeaveApplicationService.entities.Department;
import com.example.LeaveApplicationService.entities.Employee;
import com.example.LeaveApplicationService.repositories.DepartmentRepository;
import com.example.LeaveApplicationService.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public List<Department> getAllDepartments()
    {
        List<Department> departments= this.departmentRepository.findAll();
        List<Department> allDepartments= departments.stream().map(department->{
            ArrayList<Employee> employeesInDepartment=restTemplate.getForObject("http://EMPLOYEE-SERVICE/employee/department/"+department.getDeptId()+"/", ArrayList.class);
            department.setEmployeeList(employeesInDepartment);
            return department;
        }).collect(Collectors.toList());
        return allDepartments;
    }

    @Override
    public Department getDepartmentById(String id) {
        Department department= this.departmentRepository.findById(id).orElseThrow(()-> new ResolutionException("Department with given id not found"+id));
        //fetch employeelist
        //employeeby dept id use hoga

        ArrayList<Employee> employeesInDepartment=restTemplate.getForObject("http://EMPLOYEE-SERVICE/employee/department/"+department.getDeptId()+"/", ArrayList.class);
        department.setEmployeeList(employeesInDepartment);
        return department;
    }
}
