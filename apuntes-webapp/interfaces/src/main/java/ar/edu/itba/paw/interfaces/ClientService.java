package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Client;

public interface ClientService {

    Client findById(int id);

    Client findByUsername(String username);

    Client create(String username, String password, String email);
}
