package com.geotraveljournal.app.responses.core;

public class CustomException extends RuntimeException {
    private final boolean status;
    private final String message;

    public CustomException(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public boolean getStatus() {
        return status;
    }
}
