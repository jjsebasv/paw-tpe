package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Client;

import java.util.List;

public interface ClientDao extends GenericCRUDDao<Client> {

    Client findByUsername(String username);

    Client findByEmail(String email);

    List<Client> findByUniversity(long id);

    List<Client> findByProgram(long id);
}
