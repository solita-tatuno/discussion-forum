package com.devacademy.discussionforum.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorMessage> handleDataAccessException(DataAccessException ex) {
        Throwable rootCause = ex.getRootCause();

        if (rootCause instanceof SQLException sqlEx) {
            String sqlState = sqlEx.getSQLState();

            if ("23503".equals(sqlState)) {
                return new ResponseEntity<>(new ErrorMessage("The referenced entity does not exist."), HttpStatus.BAD_REQUEST);
            }

        }

        return new ResponseEntity<>(new ErrorMessage("An error occurred while processing the request."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

