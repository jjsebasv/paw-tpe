package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.ClientDao;
import ar.edu.itba.paw.models.AuthenticationToken;
import ar.edu.itba.paw.models.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClientHibernateDao extends AbstractCRUDHibernateDao<Client> implements ClientDao {


    public ClientHibernateDao() {
        super(Client.class);
    }


    @Override
    public Client findByUsername(final String username) {
        final TypedQuery<Client> query = em.createQuery("from Client as u where u.name = :username", Client.class);
        query.setParameter("username", username);
        final List<Client> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Client findByEmail(String email) {
        final TypedQuery<Client> query = em.createQuery("from Client as u where u.email = :email", Client.class);
        query.setParameter("email", email);
        final List<Client> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public AuthenticationToken storeToken(AuthenticationToken token) {
        this.em.persist(token);

        return token;
    }

    @Override
    public AuthenticationToken findTokenFor(Client client) {
        final TypedQuery<AuthenticationToken> query = em.createQuery("from AuthenticationToken as t where t.client.clientId = :clientid", AuthenticationToken.class);
        query.setParameter("clientid", client.getClientId());
        final List<AuthenticationToken> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void invalidateToken(AuthenticationToken token) {
        final AuthenticationToken modelInstance = findTokenFor(token.getClient());

        if (modelInstance != null) {
            em.remove(modelInstance);
        }
    }

    @Override
    public AuthenticationToken findByToken(String token) {
        final TypedQuery<AuthenticationToken> query = em.createQuery("from AuthenticationToken as t where t.token = :token", AuthenticationToken.class);
        query.setParameter("token", token);
        final List<AuthenticationToken> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }
}
