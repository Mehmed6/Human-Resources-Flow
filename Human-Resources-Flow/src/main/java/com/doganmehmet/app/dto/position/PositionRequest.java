package com.doganmehmet.app.dto.position;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionRequest {

    @NotBlank(message = "Title cannot be empty!")
    private String title;
    @NotBlank(message = "Description cannot be empty!")
    private String description;
    @NotBlank(message = "Department cannot be empty!")
    private String department;
}
