package com.netcracker.edu.distancestudyplatform.config;

import com.netcracker.edu.distancestudyplatform.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDenied(AccessDeniedException e) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ShortErrorMessage> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> new ShortErrorMessage(f.getField(), f.getCode(), f.getDefaultMessage(), f.getRejectedValue()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class ShortErrorMessage {
        private String field;
        private String code;
        private String defMessage;
        private Object rejectedValue;
    }
}
