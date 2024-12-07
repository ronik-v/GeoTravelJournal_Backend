package com.geotraveljournal.app.responses.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getStatus(),
                ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public static class ErrorResponse {
        private final boolean status;
        private final String message;

        public ErrorResponse(boolean status, String message) {
            this.status = status;
            this.message = message;
        }

        public boolean isStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }
}
