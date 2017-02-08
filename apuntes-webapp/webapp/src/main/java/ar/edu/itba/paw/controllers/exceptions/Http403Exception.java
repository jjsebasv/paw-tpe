package ar.edu.itba.paw.controllers.exceptions;

import ar.edu.itba.paw.controllers.HttpException;

public class Http403Exception extends HttpException {
    public Http403Exception() {
        super(403, "Unauthorized");
    }
}
