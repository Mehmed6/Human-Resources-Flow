package com.doganmehmet.app.service;

import com.doganmehmet.app.dto.tokens.JwtTokenDTO;
import com.doganmehmet.app.dto.login.LoginRequest;
import com.doganmehmet.app.entity.JwtToken;
import com.doganmehmet.app.entity.User;
import com.doganmehmet.app.enums.EmployeeStatus;
import com.doganmehmet.app.exception.ApiException;
import com.doganmehmet.app.exception.MyError;
import com.doganmehmet.app.jwt.JWTTransactions;
import com.doganmehmet.app.mapper.IJwtTokenMapper;
import com.doganmehmet.app.repository.*;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final IEmployeeRepository m_employeeRepository;
    private final IAdminRepository m_adminRepository;
    private final AuthenticationProvider m_authenticationProvider;
    private final IJwtTokenRepository m_jwtTokenRepository;
    private final IRefreshTokenRepository m_refreshTokenRepository;
    private final JWTTransactions m_jwtTransactions;
    private final IJwtTokenMapper m_jwtTokenMapper;

    public LoginService(IEmployeeRepository employeeRepository,
                        IAdminRepository adminRepository,
                        AuthenticationProvider authenticationProvider,
                        IJwtTokenRepository jwtTokenRepository,
                        IRefreshTokenRepository refreshTokenRepository,
                        JWTTransactions jwtTransactions,
                        IJwtTokenMapper jwtTokenMapper)
    {
        m_employeeRepository = employeeRepository;
        m_adminRepository = adminRepository;
        m_authenticationProvider = authenticationProvider;
        m_jwtTokenRepository = jwtTokenRepository;
        m_refreshTokenRepository = refreshTokenRepository;
        m_jwtTransactions = jwtTransactions;
        m_jwtTokenMapper = jwtTokenMapper;
    }

    private JwtTokenDTO generateTokens(User user)
    {
        var token = m_jwtTokenRepository.findByUser(user).orElse(null);

        if (token != null) {
            if (!m_jwtTransactions.isTokenExpired(token.getJwtToken()))
                return m_jwtTokenMapper.toJwtTokenDTO(token);

            m_jwtTokenRepository.deleteByUser(user);
            m_refreshTokenRepository.deleteByUser(user);

        }

        var accessToken = m_jwtTransactions.generateJwtToken(user);
        var refreshToken = m_jwtTransactions.creatRefreshToken(user);

        m_refreshTokenRepository.save(refreshToken);
        var savedJwtToken = m_jwtTokenRepository.save(new JwtToken(accessToken, user, refreshToken));

        return m_jwtTokenMapper.toJwtTokenDTO(savedJwtToken);
    }

    public JwtTokenDTO login(LoginRequest request)
    {
        var user = m_employeeRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ApiException(MyError.USER_NOT_FOUND));

        if (user.getStatus() == EmployeeStatus.BLOCKED)
            throw new ApiException(MyError.USER_BLOCKED);

        try {
            var auth = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            m_authenticationProvider.authenticate(auth);

            user.setFailedLoginAttempts(0);
            m_employeeRepository.save(user);

        } catch (BadCredentialsException ignore) {
            user.incFailedLoginAttempts();
            m_employeeRepository.save(user);

            throw new ApiException(MyError.PASSWORD_INCORRECT);
        }

        return generateTokens(user);
    }

    public JwtTokenDTO adminsLogin(LoginRequest request)
    {
        var admin = m_adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ApiException(MyError.USER_NOT_FOUND));

        try {
            var auth = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            m_authenticationProvider.authenticate(auth);
        }
        catch (BadCredentialsException ignore) {
            throw new ApiException(MyError.PASSWORD_INCORRECT);
        }

        return generateTokens(admin);
    }
}
