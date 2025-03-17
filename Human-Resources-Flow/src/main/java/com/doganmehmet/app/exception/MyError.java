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
    REFRESH_TOKEN_NOT_FOUND("1005", "Refresh token Not Found!"),
    JWT_TOKEN_EXPIRED("1006", "JWT token expired!"),
    REFRESH_TOKEN_EXPIRED("1007", "Refresh token expired!"),
    DEPARTMENT_NOT_FOUND("1008", "Department Not Found!"),
    POSITION_NOT_FOUND("1009", "Position Not Found!"),
    JWT_TOKEN_NOT_FOUND("1010", "JWT token Not Found!"),
    DEPARTMENT_ALREADY_EXISTS("1011", "Department is already in use!"),
    LEAVE_REQUEST_ALREADY_EXISTS("1012", "Leave Request is already in use!"),
    INVALID_TOKEN_FOR_USER("1013", "Authentication failed: Token does not match the user!"),
    LEAVE_REQUEST_NOT_FOUND("1014", "Leave Request Not Found!"),
    WRITE_TO_FILE("1015", "An error occurred while writing the file!"),

    ;

    private final String m_errorCode;
    private final String m_errorMessage;
}
