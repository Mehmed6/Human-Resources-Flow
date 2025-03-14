package com.doganmehmet.app.dto.tokens;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtTokenDTO {
    private String accessToken;
    private String refreshToken;
    private String username;
}
