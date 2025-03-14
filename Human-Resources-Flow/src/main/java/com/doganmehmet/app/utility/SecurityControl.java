package com.doganmehmet.app.utility;

import com.doganmehmet.app.entity.User;
import com.doganmehmet.app.exception.ApiException;
import com.doganmehmet.app.exception.MyError;
import com.doganmehmet.app.repository.IUserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityControl {
    private final IUserRepository m_userRepository;

    public SecurityControl(IUserRepository userRepository)
    {
        m_userRepository = userRepository;
    }

    public User isUser(String username)
    {
        return m_userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiException(MyError.USER_NOT_FOUND));
    }

    public User getUserIfMatchToken(String username)
    {
        var user = m_userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiException(MyError.USER_NOT_FOUND));

        var token = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!token.equals(username))
            throw new ApiException(MyError.INVALID_TOKEN_FOR_USER);

        return user;

    }
}
