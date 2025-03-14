package com.doganmehmet.app.dto.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {
    @NotBlank(message = "Username cannot be empty!")
    private String username;

    @NotBlank(message = "Firstname cannot be empty!")
    private String firstName;

    @NotBlank(message = "Lastname cannot be empty!")
    private String lastName;

    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @NotBlank(message = "Password cannot be empty!")
    private String password;

    @NotBlank(message = "Phone cannot be empty!")
    private String phone;

    @NotNull(message = "Department ID cannot be null!")
    @Positive(message = "Department ID must be positive!")
    private long departmentId;

    @NotNull(message = "Position ID cannot be null!")
    @Positive(message = "Position ID must be positive!")
    private long positionId;
}
