package com.englishweb.english_web_be.exception;

public class ResourceIsExistException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  public ResourceIsExistException(String message) {
        super(message);
    }
}
