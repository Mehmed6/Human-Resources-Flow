package com.doganmehmet.app.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final DateTimeFormatter m_formatter;

    public GlobalExceptionHandler(DateTimeFormatter formatter)
    {
        m_formatter = formatter;
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, String>> handleApiException(ApiException apiException, WebRequest webRequest)
    {
        var errorResponse = new HashMap<String, String>();
        errorResponse.put("errorCode", apiException.getMyError().getErrorCode());
        errorResponse.put("errorMessage", apiException.getMyError().getErrorMessage());
        errorResponse.put("path", webRequest.getDescription(false).replace("uri=", ""));
        errorResponse.put("errorTime", LocalDateTime.now().format(m_formatter));

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception, WebRequest webRequest)
    {
        var errorResponse = new HashMap<String, String>();
        var errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));

        errorResponse.put("errorCode", "VALIDATION_ERROR");
        errorResponse.put("errorMessage", errorMessages);
        errorResponse.put("path", webRequest.getDescription(false).replace("uri=", ""));
        errorResponse.put("errorTime", LocalDateTime.now().format(m_formatter));

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGlobalException(Exception exception, WebRequest webRequest)
    {
        var errorResponse = new HashMap<String, String>();
        errorResponse.put("errorCode", exception.getClass().getName());
        errorResponse.put("errorMessage", "An unexpected error occurred:  " + exception.getMessage());
        errorResponse.put("path", webRequest.getDescription(false).replace("uri=", ""));
        errorResponse.put("errorTime", LocalDateTime.now().format(m_formatter));

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
