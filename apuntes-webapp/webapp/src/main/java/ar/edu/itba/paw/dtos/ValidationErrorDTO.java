package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.controllers.ValidationException;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ValidationErrorDTO {

    private int code;
    private String field;
    private String message;

    public ValidationErrorDTO() {
    }

    public ValidationErrorDTO(String field, String message, int code) {
        this.field = field;
        this.message = message;
        this.code = code;
    }

    public ValidationErrorDTO(ValidationException exception) {
        this.field = exception.getField();
        this.message = exception.getErrorMessage();
        this.code = exception.getErrorCode();
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
