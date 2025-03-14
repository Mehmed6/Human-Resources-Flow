package com.doganmehmet.app.dto.employee;

import com.doganmehmet.app.dto.position.PositionDTO;
import com.doganmehmet.app.dto.department.DepartmentDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private DepartmentDTO department;
    private PositionDTO position;
}
