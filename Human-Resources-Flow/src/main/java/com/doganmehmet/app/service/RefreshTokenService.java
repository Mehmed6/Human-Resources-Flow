package com.doganmehmet.app.service;

import com.doganmehmet.app.dto.tokens.JwtTokenDTO;
import com.doganmehmet.app.dto.tokens.RefreshTokenRequest;
import com.doganmehmet.app.entity.JwtToken;
import com.doganmehmet.app.enums.LogType;
import com.doganmehmet.app.exception.ApiException;
import com.doganmehmet.app.exception.MyError;
import com.doganmehmet.app.jwt.JWTTransactions;
import com.doganmehmet.app.mapper.IJwtTokenMapper;
import com.doganmehmet.app.repository.IJwtTokenRepository;
import com.doganmehmet.app.repository.IRefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RefreshTokenService {
    private final IRefreshTokenRepository m_refreshTokenRepository;
    private final IJwtTokenRepository m_tokenRepository;
    private final JWTTransactions m_jwtTransactions;
    private final IJwtTokenMapper m_jwtTokenMapper;
    private final LogEntryService m_logEntryService;

    public RefreshTokenService(IRefreshTokenRepository refreshTokenRepository,
                               IJwtTokenRepository tokenRepository,
                               JWTTransactions jwtTransactions,
                               IJwtTokenMapper jwtTokenMapper,
                               LogEntryService logEntryService)
    {
        m_refreshTokenRepository = refreshTokenRepository;
        m_tokenRepository = tokenRepository;
        m_jwtTransactions = jwtTransactions;
        m_jwtTokenMapper = jwtTokenMapper;
        m_logEntryService = logEntryService;
    }

    public JwtTokenDTO getNewTokens(RefreshTokenRequest refreshToken)
    {
        var token = m_refreshTokenRepository.findByRefreshToken(refreshToken.getRefreshToken())
                .orElseThrow(() -> new ApiException(MyError.REFRESH_TOKEN_NOT_FOUND));

        var user = token.getUser();

        if (token.getExpiresDate().before(new Date())) {
            m_logEntryService.logger(user.getUsername(), "Expired Refresh Token", LogType.TOKEN_EXPIRED);
            throw new ApiException(MyError.REFRESH_TOKEN_EXPIRED);
        }

        m_tokenRepository.deleteByUser(user);
        m_refreshTokenRepository.deleteByUser(user);

        var accessToken = m_jwtTransactions.generateJwtToken(user);
        var newRefreshToken = m_jwtTransactions.creatRefreshToken(user);

        m_refreshTokenRepository.save(newRefreshToken);
        var savedJwtToken = m_tokenRepository.save(new JwtToken(accessToken, user, newRefreshToken));
        m_logEntryService.logger(user.getUsername(), "New Tokens created", LogType.TOKEN_REFRESH);
        return m_jwtTokenMapper.toJwtTokenDTO(savedJwtToken);
    }
}
