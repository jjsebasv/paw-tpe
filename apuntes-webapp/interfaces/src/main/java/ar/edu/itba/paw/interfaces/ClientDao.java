package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Client;

public interface ClientDao extends GenericCRUDDao<Client> {

    Client findByUsername(String username);

    Client findByEmail(String email);
}
