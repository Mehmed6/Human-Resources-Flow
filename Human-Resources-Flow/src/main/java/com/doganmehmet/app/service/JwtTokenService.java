package com.doganmehmet.app.service;

import com.doganmehmet.app.entity.JwtToken;
import com.doganmehmet.app.entity.RefreshToken;
import com.doganmehmet.app.entity.User;
import com.doganmehmet.app.repository.IJwtTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {
    private final IJwtTokenRepository m_jwtTokenRepository;

    public JwtTokenService(IJwtTokenRepository jwtTokenRepository)
    {
        m_jwtTokenRepository = jwtTokenRepository;
    }

    public JwtToken saveToken(String token, User user, RefreshToken refreshToken)
    {
        var jwtToken = new JwtToken();
        jwtToken.setJwtToken(token);
        jwtToken.setUser(user);
        jwtToken.setRefreshToken(refreshToken);

        return m_jwtTokenRepository.save(jwtToken);
    }

}
