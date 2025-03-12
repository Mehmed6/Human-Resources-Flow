package com.doganmehmet.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String AUTHENTICATE = "/authenticate";
    private static final String REGISTER = "/register/**";
    private static final String REFRESH_TOKEN = "/refreshToken";
    private static final String LOGIN = "/auth/login";

    private final AuthenticationProvider m_authenticationProvider;

    public SecurityConfig(AuthenticationProvider authenticationProvider)
    {
        m_authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain m_securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
                .authorizeHttpRequests(request -> request
                        .requestMatchers(REGISTER, REFRESH_TOKEN, AUTHENTICATE, LOGIN).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(m_authenticationProvider);

        return http.build();
    }

}
