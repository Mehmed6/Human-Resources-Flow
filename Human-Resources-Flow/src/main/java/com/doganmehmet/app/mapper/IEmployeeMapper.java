package com.doganmehmet.app.mapper;

import com.doganmehmet.app.dto.employee.EmployeeDTO;
import com.doganmehmet.app.dto.employee.EmployeeRequest;
import com.doganmehmet.app.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(implementationName = "EmployeeMapperImpl", componentModel = "spring")
public interface IEmployeeMapper {

    @Mapping(source = "position.department", target = "position.department", ignore = true)
    EmployeeDTO toEmployeeDTO(Employee employee);

    Employee toEmployee(EmployeeRequest employeeRequest);

    List<EmployeeDTO> toEmployeeDTOList(List<Employee> employees);
}
