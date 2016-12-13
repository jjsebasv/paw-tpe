package ar.edu.itba.paw.controllers;


public abstract class HttpException extends Exception {

    private final int status;

    private final String errorMessage;

    public HttpException(int statusCode, String errorMessage) {
        this.status = statusCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getStatus() {
        return status;
    }
}
