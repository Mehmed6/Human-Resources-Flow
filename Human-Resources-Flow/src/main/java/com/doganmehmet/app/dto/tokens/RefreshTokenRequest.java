package com.doganmehmet.app.dto.tokens;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRequest {
    @NotBlank(message = "Refresh Token cannot be empty!")
    private String refreshToken;
}
