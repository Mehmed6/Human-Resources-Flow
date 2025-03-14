package com.doganmehmet.app.mapper;

import com.doganmehmet.app.dto.department.DepartmentDTO;
import com.doganmehmet.app.dto.department.DepartmentRequest;
import com.doganmehmet.app.entity.Department;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(implementationName = "DepartmentMapperImpl", componentModel = "spring")
public interface IDepartmentMapper {

    DepartmentDTO toDepartmentDTO(Department department);

    Department toDepartment(DepartmentRequest departmentRequest);

    List<DepartmentDTO> toDepartmentDTOList(List<Department> departments);
}
