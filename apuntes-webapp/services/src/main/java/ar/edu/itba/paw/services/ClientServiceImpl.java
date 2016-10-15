package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.ClientDao;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public Client findById(int id) {
        return clientDao.findById(id);
    }

    @Override
    public Client findByUsername(final String username) {
        return clientDao.findByUsername(username);
    }

    public int create(String username, String password) {
        return clientDao.create(username, password).getClientId();
    }


}
