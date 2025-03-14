package com.doganmehmet.app.service;

import com.doganmehmet.app.exception.ApiException;
import com.doganmehmet.app.exception.MyError;
import com.doganmehmet.app.mapper.IDepartmentMapper;
import com.doganmehmet.app.dto.department.DepartmentDTO;
import com.doganmehmet.app.dto.department.DepartmentRequest;
import com.doganmehmet.app.repository.IDepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final IDepartmentRepository m_departmentRepository;
    private final IDepartmentMapper m_departmentMapper;

    public DepartmentService(IDepartmentRepository departmentRepository, IDepartmentMapper departmentMapper)
    {
        m_departmentRepository = departmentRepository;
        m_departmentMapper = departmentMapper;
    }

    public DepartmentDTO save(DepartmentRequest departmentRequest)
    {
        var department = m_departmentRepository.findByDepartmentName(departmentRequest.getDepartmentName());

        if (department.isPresent())
            throw new ApiException(MyError.DEPARTMENT_ALREADY_EXISTS);

        var savedDepartment = m_departmentRepository.save(m_departmentMapper.toDepartment(departmentRequest));
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
    }

}
