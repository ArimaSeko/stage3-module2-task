package com.mjc.school.service.exeptions;

public class ValidatorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ValidatorException(String message) {
        super(message);
    }
}