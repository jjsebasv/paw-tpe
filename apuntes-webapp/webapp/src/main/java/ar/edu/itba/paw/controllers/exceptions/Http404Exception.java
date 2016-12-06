package ar.edu.itba.paw.controllers.exceptions;

import ar.edu.itba.paw.controllers.HttpException;


public class Http404Exception extends HttpException {
    public Http404Exception(String message) {
        super(404, message);
    }
}
