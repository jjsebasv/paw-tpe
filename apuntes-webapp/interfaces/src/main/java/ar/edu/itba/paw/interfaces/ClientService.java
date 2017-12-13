package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Client;

import java.util.List;

public interface ClientService extends GenericCRUDService<Client> {

    Client findByUsername(String username);

    Client findByEmail(String email);

    Client getAuthenticatedUser();

    List<Client> findByUniversity(long id);

    List<Client> findByProgram(long id);
}
