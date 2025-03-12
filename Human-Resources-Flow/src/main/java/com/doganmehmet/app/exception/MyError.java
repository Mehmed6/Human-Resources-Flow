package com.doganmehmet.app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
@Accessors(prefix = "m_")
public enum MyError {

    USER_NOT_FOUND("1000", "User Not Found!"),
    USER_ALREADY_EXISTS("1001", "User is already in use!"),
    EMAIL_ALREADY_EXISTS("1002", "Email is already in use!"),
    PASSWORD_INCORRECT("1003", "Password incorrect!"),
    USER_BLOCKED("1004", "Your account has been locked due to 3 incorrect password attempts!"),
    ;

    private final String m_errorCode;
    private final String m_errorMessage;

}
