package com.doganmehmet.app.service;

import com.doganmehmet.app.dto.employee.EmployeeDTO;
import com.doganmehmet.app.dto.employee.EmployeeLeaveDateRequest;
import com.doganmehmet.app.dto.employee.EmployeeRequest;
import com.doganmehmet.app.enums.LogType;
import com.doganmehmet.app.enums.Role;
import com.doganmehmet.app.exception.ApiException;
import com.doganmehmet.app.exception.MyError;
import com.doganmehmet.app.mapper.IEmployeeMapper;
import com.doganmehmet.app.repository.IDepartmentRepository;
import com.doganmehmet.app.repository.IEmployeeRepository;
import com.doganmehmet.app.repository.IPositionRepository;
import com.doganmehmet.app.utility.SecurityUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final IEmployeeRepository m_employeeRepository;
    private final IEmployeeMapper m_employeeMapper;
    private final IDepartmentRepository m_departmentRepository;
    private final IPositionRepository m_positionRepository;
    private final BCryptPasswordEncoder m_passwordEncoder;
    private final LogEntryService m_logEntryService;

    public EmployeeService(IEmployeeRepository employeeRepository, IEmployeeMapper employeeMapper, IDepartmentRepository departmentRepository, IPositionRepository positionRepository, BCryptPasswordEncoder passwordEncoder, LogEntryService logEntryService)
    {
        m_employeeRepository = employeeRepository;
        m_employeeMapper = employeeMapper;
        m_departmentRepository = departmentRepository;
        m_positionRepository = positionRepository;
        m_passwordEncoder = passwordEncoder;
        m_logEntryService = logEntryService;
    }

    public EmployeeDTO saveEmployee(EmployeeRequest employeeRequest)
    {
        var employee = m_employeeMapper.toEmployee(employeeRequest);
        var department = m_departmentRepository.findByDepartmentId(employeeRequest.getDepartmentId())
                        .orElseThrow(() -> new ApiException(MyError.DEPARTMENT_NOT_FOUND));
        var position = m_positionRepository.findById(employeeRequest.getPositionId())
                .orElseThrow(() -> new ApiException(MyError.POSITION_NOT_FOUND));

        employee.setDepartment(department);
        employee.setPosition(position);
        employee.setRole(Role.EMPLOYEE);
        employee.setPassword(m_passwordEncoder.encode(employeeRequest.getPassword()));

        m_logEntryService.logger(SecurityUtil.getUsername(), "Employee successfully created", LogType.SUCCESSFUL);
        return m_employeeMapper.toEmployeeDTO(m_employeeRepository.save(employee));
    }

    public EmployeeDTO findByEmployeeId(long employeeId)
    {
        return m_employeeMapper.toEmployeeDTO(m_employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ApiException(MyError.USER_NOT_FOUND)));
    }

    public EmployeeDTO findByEmployeeUsername(String username)
    {
        return m_employeeMapper.toEmployeeDTO(m_employeeRepository.findByUsername(username)
        .orElseThrow(() -> new ApiException(MyError.USER_NOT_FOUND)));
    }

    public List<EmployeeDTO> findAll()
    {
        return m_employeeMapper.toEmployeeDTOList(m_employeeRepository.findAll());
    }

    public List<EmployeeDTO> findAllEmployeesByDepartment(long departmentId)
    {
        var department = m_departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ApiException(MyError.DEPARTMENT_NOT_FOUND));

        return m_employeeMapper.toEmployeeDTOList(m_employeeRepository.findAllByDepartment(department));
    }

    public List<EmployeeDTO> findAllEmployeesByPosition(long positionId)
    {
        var position = m_positionRepository.findById(positionId)
                .orElseThrow(() -> new ApiException(MyError.POSITION_NOT_FOUND));

        return m_employeeMapper.toEmployeeDTOList(m_employeeRepository.findAllByPosition(position));
    }

    public List<EmployeeDTO> findEmployeesOnLeaveOnDate(EmployeeLeaveDateRequest dateRequest)
    {
        return m_employeeMapper.toEmployeeDTOList(m_employeeRepository.findEmployeesOnLeaveOnDate(dateRequest.getDate()));
    }

}
