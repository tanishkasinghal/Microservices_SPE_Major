package com.example.EmployeeService.services;

import com.example.EmployeeService.entity.Employee;
import com.example.EmployeeService.exception.ResourceNotFoundException;
import com.example.EmployeeService.payload.EmployeeResponse;
import com.example.EmployeeService.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee registerNewUser(Employee employee) {
        return null;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        String randomDepId= UUID.randomUUID().toString();
        employee.setId(randomDepId);
        employee.setJoiningDate(new Date());
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployeeDetails(Employee employee, Long id) {
        return null;
    }

    @Override
    public Employee getEmployeeById(String id)
    {
        Employee employee=this.employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee with given id not found"+id));
      //fetch department by empid api

        return employee;
    }

    @Override
    public EmployeeResponse getAllEmployee(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort=null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }else{
            sort=Sort.by(sortBy).descending();
        }

        Pageable p= PageRequest.of(pageNumber,pageSize, sort); //Sort.by(sortBy).descending or ascendng
        Page<Employee> pageEmployees=this.employeeRepository.findAll(p);
        List<Employee> allEmployee=pageEmployees.getContent();
        EmployeeResponse employeeResponse=new EmployeeResponse();
        employeeResponse.setContent(allEmployee);
        employeeResponse.setPageNumber(pageEmployees.getNumber());
        employeeResponse.setPageSize(pageEmployees.getSize());
        employeeResponse.setTotalElements(pageEmployees.getTotalElements());
        employeeResponse.setTotalPages(pageEmployees.getTotalPages());
        employeeResponse.setLastPage(pageEmployees.isLast());
        return employeeResponse;
    }

    @Override
    public void deleteEmployee(Long id) {

    }

    @Override
    public List<Employee> getEmployeeByDepartment(String departmentId) {
        return employeeRepository.findByDeptId(departmentId);
    }

    @Override
    public List<Employee> serachEmployee(String name) {
        return null;
    }
}
