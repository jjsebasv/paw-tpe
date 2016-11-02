package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.ClientDao;
import ar.edu.itba.paw.models.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClientHibernateDao implements ClientDao {

    @PersistenceContext
    private EntityManager em;


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
    public Client findById(int id) {
        return em.find(Client.class, id);
    }


    @Override
    public Client create(String username, String password, String email) {
        final Client client = new Client(username, password, email);

        em.persist(client);

        return client;
    }
}
