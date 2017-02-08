package ar.edu.itba.paw.controllers;

public class ValidationException extends Exception {

    private final int errorCode;
    private final String errorMessage;
    private final String field;

    public ValidationException(int errorCode, String errorMessage, String field) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.field = field;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getField() {
        return field;
    }
}
