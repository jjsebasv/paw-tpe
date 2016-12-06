package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.controllers.HttpException;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessageDTO {

    private int status;

    private String message;

    public ErrorMessageDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorMessageDTO(HttpException exception) {
        this.status = exception.getStatus();
        this.message = exception.getErrorMessage();
    }

    public ErrorMessageDTO() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}