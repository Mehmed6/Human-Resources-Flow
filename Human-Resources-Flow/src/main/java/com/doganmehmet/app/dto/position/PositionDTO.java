package com.doganmehmet.app.dto.position;

import com.doganmehmet.app.dto.department.DepartmentDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionDTO {

    private String title;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DepartmentDTO department;
}
