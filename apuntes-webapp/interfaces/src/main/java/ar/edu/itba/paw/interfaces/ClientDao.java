package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Client;

public interface ClientDao {

    Client findById(int id);

    Client findByUsername(String username);

    Client findByEmail(String email);

    Client create(String username, String password, String email);

}
