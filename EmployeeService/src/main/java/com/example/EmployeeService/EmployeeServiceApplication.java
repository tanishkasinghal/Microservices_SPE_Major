package com.example.EmployeeService;

import com.example.EmployeeService.config.AppConstants;
import com.example.EmployeeService.entity.Role;
import com.example.EmployeeService.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class EmployeeServiceApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

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
	}
}
