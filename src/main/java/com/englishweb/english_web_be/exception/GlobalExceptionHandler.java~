package com.englishweb.english_web_be.exception;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Xử lý lỗi không tìm thấy tài nguyên
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseDTO<Void> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseDTO.<Void>builder()
                .success(false)
                .data(null)
                .message("Resource not found")
                .error(ex.getMessage())
                .build();
    }

    // Xử lý lỗi xác thực
    @ExceptionHandler(AuthenticationException.class)
    public ResponseDTO<Void> handleAuthenticationException(AuthenticationException ex) {
        return ResponseDTO.<Void>builder()
                .success(false)
                .data(null)
                .message("Email or password is invalid. Please check again")
                .error(ex.getMessage())
                .build();
    }

    // Xử lý lỗi sai dữ liệu đầu vào
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseDTO<Void> handleInvalidArgumentException(InvalidArgumentException ex) {
        return ResponseDTO.<Void>builder()
                .success(false)
                .data(null)
                .message("Invalid input")
                .error("Invalid request: " + ex.getMessage())
                .build();
    }

    // Xử lý lỗi sai dữ liệu đầu vào kiểu enum
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseDTO<Void> handleEnumTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String parameterName = ex.getName();
        String invalidValue = ex.getValue() != null ? ex.getValue().toString() : "null";
        String errorMessage = String.format("Invalid value '%s' for parameter '%s'. Valid values are: %s",
                invalidValue, parameterName, Arrays.toString(StatusEnum.values()));

        return ResponseDTO.<Void>builder()
                .success(false)
                .data(null)
                .message("Invalid enum value")
                .error(errorMessage)
                .build();
    }

    // Xử lý lỗi xác thực dữ liệu DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDTO<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseDTO.<Map<String, String>>builder()
                .success(false)
                .data(null)
                .message("Validation failed")
                .error(errors)
                .build();
    }
}
