package com.doganmehmet.app.dto.salary;

import com.doganmehmet.app.dto.employee.EmployeeDTO;
import com.doganmehmet.app.entity.Employee;
import com.doganmehmet.app.enums.SalaryType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SalaryDTO {

    private double salary;
    private SalaryType salaryType;
    private LocalDateTime paymentDate;
    private EmployeeDTO employee;
}
