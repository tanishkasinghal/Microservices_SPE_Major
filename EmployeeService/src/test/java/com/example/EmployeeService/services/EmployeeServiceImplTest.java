package com.example.EmployeeService.services;

import com.example.EmployeeService.config.AppConstants;
import com.example.EmployeeService.entity.Department;
import com.example.EmployeeService.entity.Employee;
import com.example.EmployeeService.entity.Role;
import com.example.EmployeeService.exception.ResourceNotFoundException;
import com.example.EmployeeService.repository.EmployeeRepository;
import com.example.EmployeeService.repository.RoleRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@SpringBootTest
public class EmployeeServiceImplTest {
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
    }
    @Test
    void testGetEmployeeById() {
        // Mock data
        String employeeId = "emp1";
        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setDeptId("dep1");

        Department department = new Department();
        department.setDeptId("dep1");
        department.setDeptName("IT");

        // Mock repository behavior
        Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        // Mock RestTemplate behavior
        Mockito.when(restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/dep1", Department.class))
                .thenReturn(department);

        // Perform the test
        Employee result = employeeService.getEmployeeById(employeeId);

        // Assertions
        Assertions.assertNotNull(result);
        Assertions.assertEquals(employeeId, result.getId());
        Assertions.assertEquals(department, result.getDepartment());

        // Verify repository and RestTemplate invocations
        Mockito.verify(employeeRepository, Mockito.times(1)).findById(employeeId);
        Mockito.verify(restTemplate, Mockito.times(1)).getForObject("http://DEPARTMENT-SERVICE/department/dep1", Department.class);
    }
    @Test
    void testDeleteEmployee() {
        // Mock data
        String employeeId = "1";

        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setFirstName("John");

        // Mock repository behavior
        Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        // Perform the test
        Assertions.assertDoesNotThrow(() -> employeeService.deleteEmployee(employeeId));

        // Verify repository invocation
        Mockito.verify(employeeRepository, Mockito.times(1)).findById(employeeId);
        Mockito.verify(employeeRepository, Mockito.times(1)).delete(employee);
    }


//    @Test
//    void testGetAllEmployee() {
//        // Mock data
//        Integer pageNumber = 0;
//        Integer pageSize = 10;
//        String sortBy = "name";
//        String sortDir = "asc";
//
//        List<Employee> employees = new ArrayList<>();
//        Employee employee1 = new Employee();
//        employee1.setId("1");
//        employee1.setFirstName("John");
//        employee1.setDeptId("dept1");
//        employees.add(employee1);
//
//        Employee employee2 = new Employee();
//        employee2.setId("2");
//        employee2.setFirstName("Jane");
//        employee2.setDeptId("dept2");
//        employees.add(employee2);
//
//        Page<Employee> employeePage = new PageImpl<>(employees, PageRequest.of(pageNumber, pageSize), employees.size());
//
//        // Mock repository behavior
//        Mockito.when(employeeRepository.findAll(any(Pageable.class))).thenReturn(employeePage);
//
//        // Mock RestTemplate behavior
//        ResponseEntity<Department> response1 = ResponseEntity.ok(new Department("dept1", "Department 1"));
//        ResponseEntity<Department> response2 = ResponseEntity.ok(new Department("dept2", "Department 2"));
//        when(restTemplate.getForEntity(eq("http://DEPARTMENT-SERVICE/department/dept1"), eq(Department.class)))
//                .thenReturn(response1);
//        when(restTemplate.getForEntity(eq("http://DEPARTMENT-SERVICE/department/dept2"), eq(Department.class)))
//                .thenReturn(response2);
//
//        // Perform the test
//        EmployeeResponse result = employeeService.getAllEmployee(pageNumber, pageSize, sortBy, sortDir);
//
//        // Assertions
//        assertNotNull(result);
//        assertEquals(2, result.getContent().size());
//        assertEquals(0, result.getPageNumber());
//        assertEquals(10, result.getPageSize());
//        assertEquals(2, result.getTotalElements());
//        assertEquals(1, result.getTotalPages());
//        assertFalse(result.isLastPage());
//
//        // Verify repository and RestTemplate invocations
//        verify(employeeRepository, times(1)).findAll(any(Pageable.class));
//        verify(restTemplate, times(1)).getForEntity(eq("http://DEPARTMENT-SERVICE/department/dept1"), eq(Department.class));
//        verify(restTemplate, times(1)).getForEntity(eq("http://DEPARTMENT-SERVICE/department/dept2"), eq(Department.class));
//    }

//    @Test
//    void testRegisterNewUser() {
//        Date currentDate = new Date();
//
//        // Create a new employee
//        Employee employee = new Employee();
//        employee.setPassword("password123");
//        employee.setFirstName("John");
//        employee.setLastName("Doe");
//        employee.setEmailId("john.doe@example.com");
//        employee.setJoiningDate(currentDate);
//
//        // Create a role for the employee
//        Role role = new Role();
//        role.setId(AppConstants.NORMAL_USER);
//        role.setName("Normal User");
//        List<Role> roles = new ArrayList<>();
//        roles.add(role);
//
//        // Mock the password encoder
//        Mockito.when(passwordEncoder.encode(employee.getPassword())).thenReturn("encodedPassword");
//
//        // Mock the role repository response
//        Mockito.when(roleRepository.findById(AppConstants.NORMAL_USER)).thenReturn(Optional.of(role));
//
//        // Mock the employee repository save method
//        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
//
//        // Call the method under test
//        Employee result = employeeService.registerNewUser(employee);
//
//        // Verify the result
//        Assertions.assertNotNull(result.getId());
//        Assertions.assertEquals("encodedPassword", result.getPassword());
//        Assertions.assertEquals(currentDate, result.getJoiningDate());
//        Assertions.assertEquals(1, result.getRoles().size());
//        Assertions.assertEquals(role, new ArrayList<>(result.getRoles()).get(0));
//        Mockito.verify(employeeRepository, Mockito.times(1)).save(employee);
//    }

}
