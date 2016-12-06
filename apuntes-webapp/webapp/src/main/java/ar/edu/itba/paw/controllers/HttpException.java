package ar.edu.itba.paw.controllers;


public abstract class HttpException extends Exception {

    private int status;

    private String errorMessage;


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
