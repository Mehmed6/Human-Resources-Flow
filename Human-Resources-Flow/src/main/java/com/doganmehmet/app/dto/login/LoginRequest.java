package com.doganmehmet.app.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Username cannot be empty!")
    private String username;
    @NotBlank(message = "Password cannot be empty!")
    private String password;
}
