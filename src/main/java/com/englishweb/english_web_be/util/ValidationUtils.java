package com.englishweb.english_web_be.util;

import com.englishweb.english_web_be.exception.InvalidArgumentException;
import com.englishweb.english_web_be.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public class ValidationUtils {

    private static ValidationUtils instance;

    private ValidationUtils() {}

    public static ValidationUtils getInstance() {
        if (instance == null) {
            instance = new ValidationUtils();
        }
        return instance;
    }

    public void validatePageRequestParam(int page, int size) {
        if (size <= 0) {
            throw new InvalidArgumentException("Size must be greater than 0");
        }
        if (page < 0) {
            throw new InvalidArgumentException("Page must be greater or equal than 0");
        }
    }

    public <T> void validateExistId(JpaRepository<T, String> repository, String id) {
        if (repository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Cannot find resource with id " + id);
        }
    }
}
