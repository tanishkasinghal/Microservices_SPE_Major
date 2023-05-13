package com.example.LeaveApplicationService.impl;

import com.example.LeaveApplicationService.config.AppConstants;
import com.example.LeaveApplicationService.entities.LeaveApplication;
import com.example.LeaveApplicationService.repositories.LeaveApplicationRepository;
import com.example.LeaveApplicationService.services.LeaveApplicationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class LeaveApplicationServiceImplTest {
    @MockBean
    private LeaveApplicationRepository leaveApplicationRepository;

    @Autowired
    LeaveApplicationService leaveApplicationService;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
    @BeforeEach
    void setup() {
        List<LeaveApplication> applicationsbyId = new ArrayList<>();
        applicationsbyId.add(new LeaveApplication("app1", "emp1", "Sick Leave", "Taking Sick Leave", null, null, new Date(), 0, null, null));
        applicationsbyId.add(new LeaveApplication("app2", "emp1", "Sick Leave", "Taking Sick Leave", null, null, new Date(), 0, null, null));
        Mockito.when(leaveApplicationRepository.findAllByEmployeeId("emp1")).thenReturn(applicationsbyId);
        List<LeaveApplication> allApplications = new ArrayList<>();
        allApplications.add(new LeaveApplication("app1", "emp1", "Sick Leave", "Taking Sick Leave", null, null, new Date(), 0, null, null));
        allApplications.add(new LeaveApplication("app2", "emp2", "Planned Leave", "Family Vacation", null, null, new Date(), 1, "Approving", null));
        Mockito.when(leaveApplicationRepository.findAll()).thenReturn(allApplications);
        }
    @Test
    public void testGetAllApplicationbyEmployeeId() {
        String applicationId1 = "app1";
        String applicationId2 = "app2";
        List<LeaveApplication> applications=leaveApplicationService.getAllApplicationbyEmployeeId("emp1");
        Assertions.assertEquals(2, applications.size());
        Assertions.assertEquals(applicationId1, applications.get(0).getApplicationId());
        Assertions.assertEquals(applicationId2, applications.get(1).getApplicationId());
    }
    @Test
    public void testGetAllApplication() {
        String employeeId1 = "app1";
        String employeeId2 = "app2";
        List<LeaveApplication> applications = leaveApplicationService.getAllApplication();
        Assertions.assertEquals(2, applications.size());
        Assertions.assertEquals(employeeId1, applications.get(0).getApplicationId());
        Assertions.assertEquals(employeeId2, applications.get(1).getApplicationId());
    }

    @Test
    public void testAddApplication() {
        String expectedApplicationId = "0b87785a-118d-4511-882b-4587dacea1ad";
        LeaveApplication leaveApplication = new LeaveApplication();
        leaveApplication.setLeaveStatus(AppConstants.Application_PENDING);
        Mockito.when(leaveApplicationRepository.save(Mockito.any(LeaveApplication.class)))
                .thenAnswer(AdditionalAnswers.returnsFirstArg());

        LeaveApplication result = leaveApplicationService.addapplication(leaveApplication);
        result.setApplicationId(expectedApplicationId);

        Assertions.assertEquals(expectedApplicationId, result.getApplicationId());
        Assertions.assertEquals(AppConstants.Application_PENDING, result.getLeaveStatus());
        Assertions.assertNotNull(result.getApplicationDate());
    }
    @Test
    public void testSubmitResponse() {
        String applicationId = "0b87785a-118d-4511-882b-4587dacea1ad";
        LeaveApplication existingApplication = new LeaveApplication();
        existingApplication.setApplicationId(applicationId);
        existingApplication.setLeaveType("Sick Leave");
        existingApplication.setMessage("Taking Sick Leave");
        existingApplication.setLeaveStatus(0);
        existingApplication.setLeaveFrom(new Date());
        existingApplication.setLeaveTill(new Date());
        existingApplication.setApplicationDate(new Date());
        existingApplication.setEmployeeId("emp1");

        LeaveApplication updatedApplication = new LeaveApplication();
        updatedApplication.setApplicationId(applicationId);
        updatedApplication.setLeaveType("Sick Leave");
        updatedApplication.setMessage("Sick Leave");
        updatedApplication.setRemarks("Approving");
        updatedApplication.setLeaveStatus(1);
        updatedApplication.setLeaveFrom(new Date());
        updatedApplication.setLeaveTill(new Date());
        updatedApplication.setApplicationDate(new Date());
        updatedApplication.setDateOfApproval(new Date());
        updatedApplication.setEmployeeId("emp1");

        // Mock the repository's findById method to return the existingApplication
        Mockito.when(leaveApplicationRepository.findById(applicationId)).thenReturn(Optional.of(existingApplication));

        // Mock the repository's save method to return the updatedApplication
        Mockito.when(leaveApplicationRepository.save(Mockito.any(LeaveApplication.class))).thenReturn(updatedApplication);

        // Invoke the submitResponse method
        LeaveApplication result = leaveApplicationService.submitResponse(updatedApplication);

        // Assertions
        Assertions.assertEquals(updatedApplication.getLeaveType(), result.getLeaveType());
        Assertions.assertEquals(updatedApplication.getMessage(), result.getMessage());
        Assertions.assertNotNull(result.getDateOfApproval());
        Assertions.assertTrue(result.getLeaveStatus() == 1 || result.getLeaveStatus() == 2);
        Assertions.assertEquals(updatedApplication.getLeaveFrom(), result.getLeaveFrom());
        Assertions.assertEquals(updatedApplication.getLeaveTill(), result.getLeaveTill());
        Assertions.assertEquals(updatedApplication.getApplicationDate(), result.getApplicationDate());
        Assertions.assertEquals(updatedApplication.getEmployeeId(), result.getEmployeeId());
    }

    @Test
    public void testGetAllPendingApplication() {
        List<LeaveApplication> pendingApplications = new ArrayList<>();
        pendingApplications.add(new LeaveApplication("app1", "emp1", "Sick Leave", "Taking Sick Leave", null, null, new Date(), AppConstants.Application_PENDING, null, null));
        pendingApplications.add(new LeaveApplication("app2", "emp2", "Vacation Leave", "Taking Vacation Leave", null, null, new Date(), AppConstants.Application_PENDING, null, null));
        Mockito.when(leaveApplicationRepository.findAllByLeaveStatus(AppConstants.Application_PENDING)).thenReturn(pendingApplications);

        List<LeaveApplication> result = leaveApplicationService.getAllPendingApplication();

        Assertions.assertEquals(2, result.size());
        for (int i = 0; i < result.size(); i++) {
            LeaveApplication application = result.get(i);
            Assertions.assertEquals(AppConstants.Application_PENDING, application.getLeaveStatus());
            Assertions.assertEquals("app" + (i + 1), application.getApplicationId());
        }
    }

    @Test
    public void testGetAllApprovedApplication() {
        List<LeaveApplication> approvedApplications = new ArrayList<>();
        approvedApplications.add(new LeaveApplication("app1", "emp1", "Sick Leave", "Taking Sick Leave", null, null, new Date(), AppConstants.Application_APPROVED, null, null));
        approvedApplications.add(new LeaveApplication("app2", "emp2", "Vacation Leave", "Taking Vacation Leave", null, null, new Date(), AppConstants.Application_APPROVED, null, null));
        Mockito.when(leaveApplicationRepository.findAllByLeaveStatus(AppConstants.Application_APPROVED)).thenReturn(approvedApplications);

        List<LeaveApplication> result = leaveApplicationService.getAllApprovedApplication();

        Assertions.assertEquals(2, result.size());
        for (int i = 0; i < result.size(); i++) {
            LeaveApplication application = result.get(i);
            Assertions.assertEquals(AppConstants.Application_APPROVED, application.getLeaveStatus());
            Assertions.assertEquals("app" + (i + 1), application.getApplicationId());
        }
    }

    @Test
    public void testGetAllRejectedApplication() {
        List<LeaveApplication> rejectedApplications = new ArrayList<>();
        rejectedApplications.add(new LeaveApplication("app1", "emp1", "Sick Leave", "Taking Sick Leave", null, null, new Date(), AppConstants.Application_REJECTED, null, null));
        rejectedApplications.add(new LeaveApplication("app2", "emp2", "Vacation Leave", "Taking Vacation Leave", null, null, new Date(), AppConstants.Application_REJECTED, null, null));
        Mockito.when(leaveApplicationRepository.findAllByLeaveStatus(AppConstants.Application_REJECTED)).thenReturn(rejectedApplications);

        List<LeaveApplication> result = leaveApplicationService.getAllRejectedApplication();

        Assertions.assertEquals(2, result.size());
        for (int i = 0; i < result.size(); i++) {
            LeaveApplication application = result.get(i);
            Assertions.assertEquals(AppConstants.Application_REJECTED, application.getLeaveStatus());
            Assertions.assertEquals("app" + (i + 1), application.getApplicationId());
        }
    }

}

