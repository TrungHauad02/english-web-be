package com.englishweb.english_web_be.exception;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Xử lý lỗi không tìm thấy tài nguyên
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error("ResourceNotFoundException: {}", ex.getMessage(), ex);
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                "Resource not found"
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Xử lý lỗi tài nguyên đã tồn tại
    @ExceptionHandler(ResourceIsExistException.class)
    public ResponseEntity<ErrorResponse> handleResourceIsExistException(ResourceIsExistException ex) {
        log.error("ResourceIsExistException: {}", ex.getMessage(), ex);
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                "Resource is exist"
        );
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex){
        log.error("AuthenticationException: {}", ex.getMessage(), ex);
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                "Email or password is invalid. Please check again"
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Xử lý lỗi sai dữ liệu đầu vào
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<ErrorResponse> handleInvalidArgumentException(InvalidArgumentException ex) {
        log.error("InvalidArgumentException: {}", ex.getMessage(), ex);
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                "Invalid request: " + ex.getMessage(),
                "Invalid input"
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Xử lý lỗi sai dữ liệu đầu vào kiểu enum
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleEnumTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String parameterName = ex.getName();
        String invalidValue = ex.getValue() != null ? ex.getValue().toString() : "null";
        String errorMessage = String.format("Invalid value '%s' for parameter '%s'. Valid values are: %s",
                invalidValue,
                parameterName,
                Arrays.toString(StatusEnum.values()));
        log.error("MethodArgumentTypeMismatchException: {}", errorMessage, ex);
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), errorMessage, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Lỗi dữ liệu dto
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        log.error("MethodArgumentNotValidException: Validation errors - {}", errors, ex);

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                "Validation failed",
                errors
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Lỗi số thứ tự duy nhất
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error("DataIntegrityViolationException: A data integrity error occurred - {}", ex.getMessage(), ex);

        String userFriendlyMessage = "A data integrity error occurred. Please ensure all data constraints are satisfied.";

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                userFriendlyMessage,
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(DataIntegrityViolationException ex){
        log.error("UserNotFoundException: {}", ex.getMessage(), ex);
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                "User not found.",
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}