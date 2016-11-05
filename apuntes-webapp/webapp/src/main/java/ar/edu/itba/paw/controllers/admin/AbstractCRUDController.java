package ar.edu.itba.paw.controllers.admin;

import ar.edu.itba.paw.interfaces.GenericCRUDService;

public abstract class AbstractCRUDController<T> {

    protected final GenericCRUDService<T> service;

    protected AbstractCRUDController(GenericCRUDService<T> service) {
        this.service = service;
    }
//
//    protected abstract String getListView();
//
//    protected abstract String getDetailsView();
}
