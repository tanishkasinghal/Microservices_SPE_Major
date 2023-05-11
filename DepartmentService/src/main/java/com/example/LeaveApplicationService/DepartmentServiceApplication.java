package com.example.LeaveApplicationService;
import com.example.LeaveApplicationService.entities.Department;
import com.example.LeaveApplicationService.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class DepartmentServiceApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private DepartmentRepository departmentRepository;
	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("singhal"));

		try {
			Department department=new Department();
			department.setDeptId("1");
			department.setDeptName("Administration");
			this.departmentRepository.save(department);

			System.out.println("Department created");
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
