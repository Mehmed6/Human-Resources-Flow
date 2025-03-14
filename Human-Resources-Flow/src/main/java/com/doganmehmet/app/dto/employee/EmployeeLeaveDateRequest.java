package com.doganmehmet.app.dto.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeLeaveDateRequest {

    @NotNull(message = "Date cannot be empty!")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
