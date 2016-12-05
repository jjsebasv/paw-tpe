package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.ClientDao;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.models.AuthenticationToken;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.ClientRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

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
    public Client create(Client model) {

        if (model.getRole() == null) {
            model.setRole(ClientRole.ROLE_USER);
        }

        return super.create(model);
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
    public AuthenticationToken generateToken(final Client client) {
        Random random = new SecureRandom();

        final String token = new BigInteger(130, random).toString(50);

        AuthenticationToken authenticationToken = new AuthenticationToken(token, client);

        clientDao.storeToken(authenticationToken);

        return authenticationToken;
    }

    @Override
    public AuthenticationToken findTokenFor(final Client client) {
        return clientDao.findTokenFor(client);
    }

    @Override
    public void invalidateToken(AuthenticationToken token) {
        clientDao.invalidateToken(token);
    }

    @Override
    public AuthenticationToken findByToken(final String token) {
        return clientDao.findByToken(token);
    }


    @Override
    public void update(final long pk, final Client from) {
        final Client instance = findById(pk);

        instance.setName(from.getName());
        instance.setEmail(from.getEmail());
        instance.setPassword(from.getPassword());
        instance.setRole(from.getRole());
    }

}
