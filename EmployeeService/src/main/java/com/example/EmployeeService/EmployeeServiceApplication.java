package com.example.EmployeeService;

import com.example.EmployeeService.config.AppConstants;

import com.example.EmployeeService.entity.Employee;
import com.example.EmployeeService.entity.Role;
import com.example.EmployeeService.repository.EmployeeRepository;


import com.example.EmployeeService.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;



import java.util.Date;


import java.util.List;

@SpringBootApplication
public class EmployeeServiceApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;


	@Autowired
	private EmployeeRepository employeeRepository;


	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("singhal"));
		try {
			Role role=new Role();
			role.setId(AppConstants.ADMIN_USER);
			//role.setName("ADMIN_USER");
			role.setName("ROLE_ADMIN");
			Role role1=new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("ROLE_USER");

			List<Role> roles= List.of(role,role1);
			List<Role> result=this.roleRepository.saveAll(roles);

			result.forEach(r->{
				System.out.println(r.getName());
			});
		}catch (Exception e){
			e.printStackTrace();
		}


		try {
			Employee employee=new Employee();
			employee.setId(AppConstants.ADMIN_EMPLOYEE_ID);
			employee.setPassword(this.passwordEncoder.encode(("admin")));
			employee.setJoiningDate(new Date());
			Role role=this.roleRepository.findById(AppConstants.ADMIN_USER).get();
			employee.getRoles().add(role);
			employee.setFirstName("Admin");
			employee.setDeptId("1");
			employee.setEmailId("admin@gmail.com");
			this.employeeRepository.save(employee);

			System.out.println("Adminuser created");
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
