package com.doganmehmet.app.dto.leaverequest;

import com.doganmehmet.app.dto.employee.EmployeeDTO;
import com.doganmehmet.app.enums.LeaveStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LeaveRequestDTO {

    private String leaveReason;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveStatus leaveStatus;
    private EmployeeDTO employee;

}
