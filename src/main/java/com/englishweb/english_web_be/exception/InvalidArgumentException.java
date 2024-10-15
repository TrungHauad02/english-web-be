package com.englishweb.english_web_be.exception;

public class InvalidArgumentException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public InvalidArgumentException(){
    }

    public InvalidArgumentException(String message){
        super(message);
    }
}
