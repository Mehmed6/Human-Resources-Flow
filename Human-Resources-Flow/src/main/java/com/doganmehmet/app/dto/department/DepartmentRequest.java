package com.doganmehmet.app.dto.department;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequest {
    @NotBlank(message = "Department name cannot be empty!")
    private String departmentName;
    @NotBlank(message = "Department description cannot be empty!")
    private String departmentDescription;
}
