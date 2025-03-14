package com.doganmehmet.app.service;

import com.doganmehmet.app.dto.register.RegisterRequest;
import com.doganmehmet.app.dto.employee.EmployeeDTO;
import com.doganmehmet.app.entity.Employee;
import com.doganmehmet.app.enums.Role;
import com.doganmehmet.app.exception.ApiException;
import com.doganmehmet.app.exception.MyError;
import com.doganmehmet.app.mapper.IEmployeeMapper;
import com.doganmehmet.app.repository.IDepartmentRepository;
import com.doganmehmet.app.repository.IEmployeeRepository;
import com.doganmehmet.app.repository.IPositionRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final IEmployeeRepository m_employeeRepository;
    private final IEmployeeMapper m_employeeMapper;
    private final BCryptPasswordEncoder m_bCryptPasswordEncoder;
    private final IDepartmentRepository m_departmentRepository;
    private final IPositionRepository m_positionRepository;

    public RegisterService(IEmployeeRepository employeeRepository, IEmployeeMapper employeeMapper, BCryptPasswordEncoder bCryptPasswordEncoder, IDepartmentRepository departmentRepository, IPositionRepository positionRepository)
    {
        m_employeeRepository = employeeRepository;
        m_employeeMapper = employeeMapper;
        m_bCryptPasswordEncoder = bCryptPasswordEncoder;
        m_departmentRepository = departmentRepository;
        m_positionRepository = positionRepository;
    }

    private Employee createAndSaveEmployee(RegisterRequest registerRequest)
    {
        var employee = new Employee();
        employee.setUsername(registerRequest.getUsername());
        employee.setFirstName(registerRequest.getFirstName());
        employee.setLastName(registerRequest.getLastName());
        employee.setPassword(m_bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        employee.setEmail(registerRequest.getEmail());
        employee.setPhone(registerRequest.getPhone());
        employee.setRole(Role.EMPLOYEE);

        var department = m_departmentRepository.findByDepartmentId(registerRequest.getDepartmentId())
                .orElseThrow(() -> new ApiException(MyError.DEPARTMENT_NOT_FOUND));

        var position = m_positionRepository.findById(registerRequest.getPositionId())
                .orElseThrow(() -> new ApiException(MyError.POSITION_NOT_FOUND));

        position.setDepartment(department);
        employee.setDepartment(department);
        employee.setPosition(position);

        return m_employeeRepository.save(employee);
    }

    public EmployeeDTO register(RegisterRequest registerRequest)
    {
        if (m_employeeRepository.existsByUsername(registerRequest.getUsername()))
            throw new ApiException(MyError.USER_ALREADY_EXISTS);

        if (m_employeeRepository.existsByEmail(registerRequest.getEmail()))
            throw new ApiException(MyError.EMAIL_ALREADY_EXISTS);


        var savedUser = createAndSaveEmployee(registerRequest);

        return m_employeeMapper.toEmployeeDTO(savedUser);
    }
}
