package com.example.EmployeeService.services;

import com.example.EmployeeService.config.AppConstants;
import com.example.EmployeeService.entity.Department;
import com.example.EmployeeService.entity.Employee;
import com.example.EmployeeService.entity.Role;
import com.example.EmployeeService.exception.ResourceNotFoundException;
import com.example.EmployeeService.payload.EmployeeResponse;
import com.example.EmployeeService.repository.EmployeeRepository;
import com.example.EmployeeService.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Employee registerNewUser(Employee employee) {
        String randomDepId= UUID.randomUUID().toString();
        employee.setId(randomDepId);
        employee.setPassword(this.passwordEncoder.encode(employee.getPassword()));
        employee.setJoiningDate(new Date());
        //roles
        Role role=this.roleRepository.findById(AppConstants.NORMAL_USER).get();
        employee.getRoles().add(role);
        return this.employeeRepository.save(employee);

    }

    @Override
    public Employee addEmployee(Employee employee) {
        String randomDepId= UUID.randomUUID().toString();
        employee.setId(randomDepId);
        employee.setJoiningDate(new Date());
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployeeDetails(Employee details, String id) {
        Employee employee=this.employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee with given id not found"+id));
        employee.setFirstName(details.getFirstName());
        employee.setLastName(details.getLastName());
        employee.setEmailId(details.getEmailId());
        employee.setPassword(details.getPassword());
        employee.setDeptId(details.getDeptId());
        Employee updatedEmployee=this.employeeRepository.save(employee);
        return updatedEmployee;
    }

    @Override
    public Employee getEmployeeById(String id)
    {
        Employee employee=this.employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee with given id not found"+id));
      //fetch department by empid api
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/" + employee.getDeptId(), Department.class);
        employee.setDepartment(department);
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
        List<Employee> allEmployees=pageEmployees.getContent();
        List<Employee> allEmployee= allEmployees.stream().map(employee->{
            Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/" + employee.getDeptId(), Department.class);
            employee.setDepartment(department);
            return employee;
        }).collect(Collectors.toList());
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
    public void deleteEmployee(String id) {
        Employee employee=this.employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee with given id not found"+id));
        this.employeeRepository.delete(employee);
    }

    @Override
    public List<Employee> getEmployeeByDepartment(String departmentId) {
        return employeeRepository.findByDeptId(departmentId);
    }

    @Override
    public List<Employee> serachEmployee(String name) {
        return this.employeeRepository.findByFirstNameContaining(name);

    }
}
