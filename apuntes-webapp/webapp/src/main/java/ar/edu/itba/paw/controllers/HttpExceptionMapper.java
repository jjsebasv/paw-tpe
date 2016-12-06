package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.dtos.ErrorMessageDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class HttpExceptionMapper implements ExceptionMapper<HttpException> {

    public Response toResponse(HttpException ex) {
        return Response.status(ex.getStatus())
                .entity(new ErrorMessageDTO(ex))
                .build();
    }

}
