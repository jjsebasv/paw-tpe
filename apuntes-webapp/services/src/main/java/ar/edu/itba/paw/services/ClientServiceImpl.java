package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.ClientDao;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.ClientRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Client getAuthenticatedUser() {

        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            return null;
        }

        return findByUsername((String) auth.getPrincipal());
    }

    @Override
    public List<Client> findByUniversity(long id) {
        return clientDao.findByUniversity(id);
    }

    @Override
    public List<Client> findByProgram(long id) {
        return clientDao.findByProgram(id);
    }

    @Override
    public void update(final long pk, final Client from) {
        final Client instance = findById(pk);

        instance.setName(from.getName());
        instance.setEmail(from.getEmail());
        instance.setPassword(from.getPassword());
        instance.setRole(from.getRole());
        instance.setRecoveryQuestion(from.getRecoveryQuestion());
        instance.setSecretAnswer(from.getSecretAnswer());
        instance.setProgram(from.getProgram());
    }

}
