package com.doganmehmet.app.dto.salary;

import com.doganmehmet.app.enums.SalaryType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SalaryRequest {

    @NotNull(message = "Salary cannot be empty!")
    private double salary;
    @NotNull(message = "Salary Type cannot be empty!")
    @Enumerated(EnumType.STRING)
    private SalaryType salaryType;
    @NotNull(message = "Payment Date cannot be empty!")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime paymentDate;
    @NotNull(message = "Employee ID cannot be empty!")
    private long employeeId;
}
