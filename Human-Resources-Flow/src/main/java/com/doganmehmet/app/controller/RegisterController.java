package com.doganmehmet.app.controller;

import com.doganmehmet.app.dto.employee.EmployeeDTO;
import com.doganmehmet.app.dto.register.RegisterRequest;
import com.doganmehmet.app.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final RegisterService m_registerService;

    public RegisterController(RegisterService registerService)
    {
        m_registerService = registerService;
    }

    @PostMapping
    public EmployeeDTO register(@Valid @RequestBody RegisterRequest registerRequest)
    {
        return m_registerService.register(registerRequest);
    }

}
