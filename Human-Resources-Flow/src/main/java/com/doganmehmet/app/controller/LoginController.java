package com.doganmehmet.app.controller;

import com.doganmehmet.app.dto.tokens.JwtTokenDTO;
import com.doganmehmet.app.dto.login.LoginRequest;
import com.doganmehmet.app.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/login")
public class LoginController {
    private final LoginService m_loginService;

    public LoginController(LoginService loginService)
    {
        m_loginService = loginService;
    }

    @PostMapping
    public JwtTokenDTO login(@Valid @RequestBody LoginRequest loginRequest)
    {
        return m_loginService.login(loginRequest);
    }

    @PostMapping("/admin")
    public JwtTokenDTO adminsLogin(@Valid @RequestBody LoginRequest loginRequest)
    {
        return m_loginService.adminsLogin(loginRequest);
    }
}
