package com.doganmehmet.app.jwt;

import com.doganmehmet.app.exception.ApiException;
import com.doganmehmet.app.exception.MyError;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTTransactions m_jwtTransactions;
    private final UserDetailsService m_userDetailsService;

    public JWTAuthenticationFilter(JWTTransactions jwtTransactions, UserDetailsService userDetailsService)
    {
        m_jwtTransactions = jwtTransactions;
        m_userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        var header = request.getHeader("Authorization");

        if (header == null) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = header.substring(7);

        try {
            var username = m_jwtTransactions.getUsernameByToken(token);
            var roles = m_jwtTransactions.getRolesFromToken(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                var userDetails = m_userDetailsService.loadUserByUsername(username);

                if (userDetails != null && !m_jwtTransactions.isTokenExpired(token)) {

                    var authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();
                    var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        catch (Exception ignored) {
            throw new ApiException(MyError.JWT_TOKEN_NOT_FOUND);
        }

        filterChain.doFilter(request, response);
    }
}
