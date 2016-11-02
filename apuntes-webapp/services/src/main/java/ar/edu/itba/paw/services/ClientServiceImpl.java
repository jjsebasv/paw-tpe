package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.ClientDao;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(final ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public Client findById(final int id) {
        return clientDao.findById(id);
    }

    @Override
    public Client findByUsername(final String username) {
        return clientDao.findByUsername(username);
    }

    @Override
    public Client findByEmail(final String email) {
        return clientDao.findByEmail(email);
    }

    @Override
    public List<Client> getAll() {
        return clientDao.getAll();
    }

    @Override
    public Client create(final String username, final String password, final String email) {
        return clientDao.create(username, password, email);
    }

    @Override
    public void update(final int clientid, final Client from) {
        final Client instance = findById(clientid);

        instance.setName(from.getName());
        instance.setEmail(from.getEmail());
        instance.setPassword(from.getPassword());
    }

    @Override
    public void delete(final int clientid) {
        clientDao.delete(clientid);
    }
}
