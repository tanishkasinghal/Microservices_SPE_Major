package com.example.LeaveApplicationService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="leave_application")
public class LeaveApplication {
    @Id
    private String applicationId;
    private String employeeId;
    private String leaveType;
    private String message;
    private Date leaveFrom;
    private Date leaveTill;
    private Date applicationDate;
    private Integer leaveStatus;
    private String remarks;
    private Date dateOfApproval;
}
