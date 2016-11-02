package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Client;

import java.util.List;

public interface ClientService {

    Client findById(int id);

    Client findByUsername(String username);

    Client findByEmail(String email);

    List<Client> getAll();

    Client create(String username, String password, String email);

    void update(int clientid, Client from);

    void delete(int clientid);
}
