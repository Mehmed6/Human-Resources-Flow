package com.doganmehmet.app.service;

import com.doganmehmet.app.enums.LogType;
import com.doganmehmet.app.exception.ApiException;
import com.doganmehmet.app.exception.MyError;
import com.doganmehmet.app.mapper.IDepartmentMapper;
import com.doganmehmet.app.dto.department.DepartmentDTO;
import com.doganmehmet.app.dto.department.DepartmentRequest;
import com.doganmehmet.app.repository.IDepartmentRepository;
import com.doganmehmet.app.utility.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final IDepartmentRepository m_departmentRepository;
    private final IDepartmentMapper m_departmentMapper;
    private final LogEntryService m_logEntryService;

    public DepartmentService(IDepartmentRepository departmentRepository, IDepartmentMapper departmentMapper, LogEntryService logEntryService)
    {
        m_departmentRepository = departmentRepository;
        m_departmentMapper = departmentMapper;
        m_logEntryService = logEntryService;
    }

    public DepartmentDTO save(DepartmentRequest departmentRequest)
    {
        var department = m_departmentRepository.findByDepartmentName(departmentRequest.getDepartmentName());

        if (department.isPresent()) {
            m_logEntryService.logger(SecurityUtil.getUsername(), "Department already exists", LogType.ALREADY_EXISTS_EXCEPTION);
            throw new ApiException(MyError.DEPARTMENT_ALREADY_EXISTS);
        }

        var savedDepartment = m_departmentRepository.save(m_departmentMapper.toDepartment(departmentRequest));
        m_logEntryService.logger(SecurityUtil.getUsername(), "Department successfully created", LogType.SUCCESSFUL);
        return m_departmentMapper.toDepartmentDTO(savedDepartment);
    }

    public List<DepartmentDTO> findAllDepartment()
    {
        return m_departmentMapper.toDepartmentDTOList(m_departmentRepository.findAll());
    }

    public DepartmentDTO findDepartmentByName(String departmentName)
    {
        return m_departmentMapper.toDepartmentDTO(m_departmentRepository.findByDepartmentName(departmentName)
                .orElseThrow(() -> new ApiException(MyError.DEPARTMENT_NOT_FOUND)));
    }

    public DepartmentDTO findDepartmentById(long departmentId)
    {
        return m_departmentMapper.toDepartmentDTO(m_departmentRepository.findByDepartmentId(departmentId)
                .orElseThrow(() -> new ApiException(MyError.DEPARTMENT_NOT_FOUND)));
    }

    public void deleteDepartmentById(long departmentId)
    {
        m_departmentRepository.deleteById(departmentId);
        m_logEntryService.logger(SecurityUtil.getUsername(), "Department successfully deleted", LogType.SUCCESSFUL);
    }

}
