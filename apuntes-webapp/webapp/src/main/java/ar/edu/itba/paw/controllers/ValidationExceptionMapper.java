package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.dtos.ValidationErrorDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    private static final int ERROR_CODE = 422;

    public Response toResponse(ValidationException ex) {
        return Response.status(ERROR_CODE)
                .entity(new ValidationErrorDTO(ex))
                .build();
    }

}
