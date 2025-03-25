package com.doganmehmet.app.controller;

import com.doganmehmet.app.dto.tokens.JwtTokenDTO;
import com.doganmehmet.app.dto.tokens.RefreshTokenRequest;
import com.doganmehmet.app.service.RefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/refreshToken")
public class RefreshTokenController {
    private final RefreshTokenService m_refreshTokenService;

    public RefreshTokenController(RefreshTokenService refreshTokenService)
    {
        m_refreshTokenService = refreshTokenService;
    }

    @PostMapping
    public JwtTokenDTO getTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest)
    {
        return m_refreshTokenService.getNewTokens(refreshTokenRequest);
    }
}
