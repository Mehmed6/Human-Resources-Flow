package com.doganmehmet.app.controller;

import com.doganmehmet.app.dto.employee.EmployeeDTO;
import com.doganmehmet.app.dto.register.RegisterRequest;
import com.doganmehmet.app.mapper.IEmployeeMapper;
import com.doganmehmet.app.repository.IEmployeeRepository;
import com.doganmehmet.app.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final RegisterService m_registerService;
    private final IEmployeeRepository m_employeeRepository;
    private final IEmployeeMapper m_employeeMapper;

    public RegisterController(RegisterService registerService, IEmployeeRepository employeeRepository, IEmployeeMapper employeeMapper)
    {
        m_registerService = registerService;
        m_employeeRepository = employeeRepository;
        m_employeeMapper = employeeMapper;
    }

    @PostMapping
    public EmployeeDTO register(@Valid @RequestBody RegisterRequest registerRequest)
    {
        return m_registerService.register(registerRequest);
    }

    @GetMapping("/find")
    public EmployeeDTO find(@RequestParam Long id)
    {
        return m_employeeMapper.toEmployeeDTO(m_employeeRepository.findById(id).orElse(null));
    }
}
