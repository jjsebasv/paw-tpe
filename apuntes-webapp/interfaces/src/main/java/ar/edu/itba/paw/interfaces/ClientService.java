package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Client;

public interface ClientService {

    Client findById(int id);

    Client findByUsername(String username);

    int create(String username, String password);
}
