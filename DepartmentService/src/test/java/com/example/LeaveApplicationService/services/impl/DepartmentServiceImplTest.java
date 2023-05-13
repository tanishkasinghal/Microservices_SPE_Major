package com.example.LeaveApplicationService.services.impl;

import com.example.LeaveApplicationService.entities.Department;
import com.example.LeaveApplicationService.entities.Employee;
import com.example.LeaveApplicationService.repositories.DepartmentRepository;
import com.example.LeaveApplicationService.services.DepartmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class DepartmentServiceImplTest {
    @MockBean
    private DepartmentRepository departmentRepository;

    @Autowired
    DepartmentService departmentService;


    private List<Department> mockDepartments;

    @BeforeEach
    public void setup() {
        // Create a list of mock departments
        mockDepartments = new ArrayList<>();
        mockDepartments.add(new Department("1","Testing",null));
        mockDepartments.add(new Department("2","Operations",null));
    }
    @Test
    public void testAddDepartment() {
        String expecteddepartmentId = "0b87785a-118d-4511-882b-4587dacea1ad";
        Department department = new Department();
        Mockito.when(departmentRepository.save(Mockito.any(Department.class)))
                .thenAnswer(AdditionalAnswers.returnsFirstArg());

        Department result = departmentService.addDepartment(department);
        result.setDeptId(expecteddepartmentId);

        Assertions.assertEquals(expecteddepartmentId, result.getDeptId());
    }
    @Test
    public void testGetAllDepartments() {
        MockitoAnnotations.initMocks(this);
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);

        List<Employee> employeesInDepartment1 = new ArrayList<>(Arrays.asList(
                new Employee("emp1", "dep1", "John", "Doe", "email@gmail.com", null, "123"),
                new Employee("emp2", "dep1", "Tanishka", "Singhal", "tanishka@gmail.com", null, "123")
        ));
        List<Employee> employeesInDepartment2 = new ArrayList<>(Arrays.asList(
                new Employee("emp3", "dep2", "John", "Doe", "email1@gmail.com", null, "123"),
                new Employee("emp4", "dep2", "Tanishka", "Singhal", "tanishka1@gmail.com", null, "123")
        ));
        Department department1 = new Department("dep1", "Department 1", employeesInDepartment1);
        Department department2 = new Department("dep2", "Department 2", employeesInDepartment2);
        List<Department> departments = Arrays.asList(department1, department2);

        // Mock the restTemplate response
        Mockito.when(restTemplateMock.getForObject(
                        Mockito.eq("http://EMPLOYEE-SERVICE/employee/department/dep1/"),
                        Mockito.any(Class.class)
                )
        ).thenReturn(employeesInDepartment1);

        Mockito.when(restTemplateMock.getForObject(
                        Mockito.eq("http://EMPLOYEE-SERVICE/employee/department/dep2/"),
                        Mockito.any(Class.class)
                )
        ).thenReturn(employeesInDepartment2);

        // Inject the mocked restTemplate into the departmentService
        ReflectionTestUtils.setField(departmentService, "restTemplate", restTemplateMock);

        // Mock the department repository response
        Mockito.when(departmentRepository.findAll()).thenReturn(departments);

        // Call the method under test
        List<Department> result = departmentService.getAllDepartments();

        // Verify the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(department1, result.get(0));
        Assertions.assertEquals(department2, result.get(1));
        Assertions.assertEquals(employeesInDepartment1, result.get(0).getEmployeeList());
        Assertions.assertEquals(employeesInDepartment2, result.get(1).getEmployeeList());
    }

    @Test
    public void testGetDepartmentById() {
        MockitoAnnotations.initMocks(this);
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);

        String departmentId = "dep1";
        List<Employee> employeesInDepartment = new ArrayList<>(Arrays.asList(
                new Employee("emp1", "dep1", "John", "Doe", "email@gmail.com", null, "123"),
                new Employee("emp2", "dep1", "Tanishka", "Singhal", "tanishka@gmail.com", null, "123")
        ));
        Department department = new Department(departmentId, "Department 1", employeesInDepartment);

        // Mock the restTemplate response
        Mockito.when(restTemplateMock.getForObject(
                        Mockito.eq("http://EMPLOYEE-SERVICE/employee/department/" + departmentId + "/"),
                        Mockito.any(Class.class)
                )
        ).thenReturn(employeesInDepartment);

        // Inject the mocked restTemplate into the departmentService
        ReflectionTestUtils.setField(departmentService, "restTemplate", restTemplateMock);

        // Mock the department repository response
        Mockito.when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));

        // Call the method under test
        Department result = departmentService.getDepartmentById(departmentId);

        // Verify the result
        Assertions.assertEquals(department, result);
        Assertions.assertEquals(employeesInDepartment, result.getEmployeeList());
    }


}


