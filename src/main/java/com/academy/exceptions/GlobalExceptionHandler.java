package com.academy.exceptions;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ================================
    // Custom Business Exception
    // ================================
    @ExceptionHandler(AcademyException.class)
    public ResponseEntity<ErrorInfo> handleAcademyException(
            AcademyException ex) {

        ErrorInfo errorInfo = new ErrorInfo(
                ex.getMessage(),
                ex.getErrorCode().value(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(ex.getErrorCode())
                .body(errorInfo);
    }


    // ================================
    // Validation Exception
    // @Valid
    // ================================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> handleValidationException(
            MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
                        error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");

        ErrorInfo errorInfo = new ErrorInfo(
                message,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorInfo);
    }


    // ================================
    // Database Constraint Exception
    // ================================
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorInfo> handleDataIntegrityException(
            DataIntegrityViolationException ex) {

        ErrorInfo errorInfo = new ErrorInfo(
                "Database constraint violation",
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorInfo);
    }


    // ================================
    // Illegal Argument
    // ================================
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorInfo> handleIllegalArgumentException(
            IllegalArgumentException ex) {

        ErrorInfo errorInfo = new ErrorInfo(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorInfo);
    }


    // ================================
    // Null Pointer
    // ================================
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorInfo> handleNullPointerException(
            NullPointerException ex) {

        ErrorInfo errorInfo = new ErrorInfo(
                "Unexpected null value",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorInfo);
    }


    // ================================
    // All Other Exceptions
    // ================================
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleException(
            Exception ex) {

        ErrorInfo errorInfo = new ErrorInfo(
                "Internal server error",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorInfo);
    }
}