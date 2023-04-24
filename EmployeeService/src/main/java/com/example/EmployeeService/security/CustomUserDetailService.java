package com.example.EmployeeService.security;


import com.example.EmployeeService.entity.Employee;
import com.example.EmployeeService.exception.ResourceNotFoundException;
import com.example.EmployeeService.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        //loading user from database by username
        Employee employee=this.employeeRepository.findByEmailId(username).orElseThrow(()-> new ResourceNotFoundException("user with email not found: "+username));
        return employee;
    }


}
