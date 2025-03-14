package com.doganmehmet.app.service;

import com.doganmehmet.app.dto.salary.SalaryDTO;
import com.doganmehmet.app.dto.salary.SalaryRequest;
import com.doganmehmet.app.enums.SalaryType;
import com.doganmehmet.app.exception.ApiException;
import com.doganmehmet.app.exception.MyError;
import com.doganmehmet.app.mapper.ISalaryMapper;
import com.doganmehmet.app.repository.IEmployeeRepository;
import com.doganmehmet.app.repository.ISalaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryService {
    private final ISalaryRepository m_salaryRepository;
    private final ISalaryMapper m_salaryMapper;
    private final IEmployeeRepository m_employeeRepository;

    public SalaryService(ISalaryRepository salaryRepository, ISalaryMapper salaryMapper, IEmployeeRepository employeeRepository)
    {
        m_salaryRepository = salaryRepository;
        m_salaryMapper = salaryMapper;
        m_employeeRepository = employeeRepository;
    }

    public SalaryDTO saveSalary(SalaryRequest salaryRequest)
    {
        var employee = m_employeeRepository.findById(salaryRequest.getEmployeeId())
                .orElseThrow(() -> new ApiException(MyError.USER_NOT_FOUND));

        var salary = m_salaryMapper.toSalary(salaryRequest);
        salary.setEmployee(employee);

        return m_salaryMapper.toSalaryDTO(m_salaryRepository.save(salary));
    }

    public List<SalaryDTO> findAllSalary()
    {
        return m_salaryMapper.toSalaryDTOList(m_salaryRepository.findAll());
    }

    public List<SalaryDTO> findSalaryByEmployeeId(long employeeId)
    {
        var employee = m_employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ApiException(MyError.USER_NOT_FOUND));

        return m_salaryMapper.toSalaryDTOList(m_salaryRepository.findAllByEmployee(employee));
    }

    public List<SalaryDTO> findSalaryBySalaryType(SalaryType salaryType)
    {
        return m_salaryMapper.toSalaryDTOList(m_salaryRepository.findAllBySalaryType(salaryType));
    }

    public List<SalaryDTO> findSalaryBetween(double minSalary, double maxSalary)
    {
        return m_salaryMapper.toSalaryDTOList(m_salaryRepository.findAllBySalaryBetween(minSalary, maxSalary));
    }

    public List<SalaryDTO> findSalaryGreaterThan(double salary)
    {
        return m_salaryMapper.toSalaryDTOList(m_salaryRepository.findAllBySalaryGreaterThan(salary));
    }

    public List<SalaryDTO> findSalaryLessThan(double salary)
    {
        return m_salaryMapper.toSalaryDTOList(m_salaryRepository.findAllBySalaryLessThan(salary));
    }
}
