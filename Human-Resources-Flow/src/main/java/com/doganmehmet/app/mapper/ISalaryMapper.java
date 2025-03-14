package com.doganmehmet.app.mapper;

import com.doganmehmet.app.dto.salary.SalaryDTO;
import com.doganmehmet.app.dto.salary.SalaryRequest;
import com.doganmehmet.app.entity.Salary;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(implementationName = "SalaryMapperImpl", componentModel = "spring")
public interface ISalaryMapper {

    Salary toSalary(SalaryRequest salaryRequest);

    SalaryDTO toSalaryDTO(Salary salary);

    List<SalaryDTO> toSalaryDTOList(List<Salary> salaries);
}
