package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Client;

public interface ClientService {

    Client findById(int id);

    int create(String username, String password);
}
