package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Client;

public interface ClientService extends GenericCRUDService<Client> {

    Client findByUsername(String username);

    Client findByEmail(String email);
}
