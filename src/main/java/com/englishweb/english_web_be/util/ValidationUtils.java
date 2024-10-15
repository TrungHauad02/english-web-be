package com.englishweb.english_web_be.util;

import com.englishweb.english_web_be.exception.InvalidArgumentException;
import com.englishweb.english_web_be.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Field;

public class ValidationUtils {

    private static ValidationUtils instance;

    private ValidationUtils() {}

    public static ValidationUtils getInstance() {
        if (instance == null) {
            instance = new ValidationUtils();
        }
        return instance;
    }

    public <T> void validatePageRequestParam(int page, int size, String sortBy, Class<T> dtoClass) {
        if (size <= 0) {
            throw new InvalidArgumentException("Size must be greater than 0");
        }
        if (page < 0) {
            throw new InvalidArgumentException("Page must be greater or equal than 0");
        }

        boolean fieldExists = false;
        Field[] fields = dtoClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(sortBy)) {
                fieldExists = true;
                break;
            }
        }

        if (!fieldExists) {
            throw new InvalidArgumentException("Field '" + sortBy + "' does not exist in the object");
        }
    }

    public <T> void validateExistId(JpaRepository<T, String> repository, String id) {
        if (repository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Cannot find resource with id " + id);
        }
    }
}
