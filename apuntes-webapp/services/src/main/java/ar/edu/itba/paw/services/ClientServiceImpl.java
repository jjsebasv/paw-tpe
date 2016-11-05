package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.ClientDao;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl extends AbstractCRUDService<Client> implements ClientService {

    private final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(final ClientDao clientDao) {
        super(clientDao);
        this.clientDao = clientDao;
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
    public void update(final long pk, final Client from) {
        final Client instance = findById(pk);

        instance.setName(from.getName());
        instance.setEmail(from.getEmail());
        instance.setPassword(from.getPassword());
    }

}
