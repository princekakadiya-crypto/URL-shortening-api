package com.tss.URL_Shortening.exception;

import com.tss.URL_Shortening.error.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> illegalArgumentException(IllegalArgumentException illegalArgumentException, HttpServletRequest request){

        logger.warn(
                "Invalid argument: {} | URI: {}",
                illegalArgumentException.getMessage(),
                request.getRequestURI()
        );

        ErrorResponseDto error=new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                illegalArgumentException.getMessage(),
                LocalDateTime.now(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(
                error,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> NotFoundException(ResourceNotFoundException resourceNotFoundException, HttpServletRequest request){

        logger.warn(
                "Resource not found: {} | URI: {}",
                resourceNotFoundException.getMessage(),
                request.getRequestURI()
        );

        ErrorResponseDto error=new ErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                resourceNotFoundException.getMessage(),
                LocalDateTime.now(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(
                error,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponseDto> duplicateResourceException(DuplicateResourceException duplicateResourceException, HttpServletRequest request){

        logger.warn(
                "Duplicate Resource : {} | URI: {}",
                duplicateResourceException.getMessage(),
                request.getRequestURI()
        );

        ErrorResponseDto error=new ErrorResponseDto(
                HttpStatus.CONFLICT.value(),
                duplicateResourceException.getMessage(),
                LocalDateTime.now(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(
                error,
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ErrorResponseDto> invalidCredentialException(
            InvalidCredentialException exception,
            HttpServletRequest request) {

        logger.warn(
                "Invalid credentials: {} | URI: {}",
                exception.getMessage(),
                request.getRequestURI()
        );

        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.UNAUTHORIZED.value(),
                exception.getMessage(),
                LocalDateTime.now(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(
                error,
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ErrorResponseDto> invalidOperationException(
            InvalidOperationException exception,
            HttpServletRequest request) {

        logger.warn(
                "Invalid operation: {} | URI: {}",
                exception.getMessage(),
                request.getRequestURI()
        );

        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                LocalDateTime.now(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(
                error,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<ErrorResponseDto> invalidOtpException(
            InvalidOtpException exception,
            HttpServletRequest request) {

        logger.warn(
                "Invalid OTP: {} | URI: {}",
                exception.getMessage(),
                request.getRequestURI()
        );

        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                LocalDateTime.now(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(
                error,
                HttpStatus.BAD_REQUEST
        );
    }

}
