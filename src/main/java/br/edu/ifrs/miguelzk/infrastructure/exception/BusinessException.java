package br.edu.ifrs.miguelzk.infrastructure.exception;

import org.hibernate.exception.ConstraintViolationException;

public class BusinessException extends RuntimeException {
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
