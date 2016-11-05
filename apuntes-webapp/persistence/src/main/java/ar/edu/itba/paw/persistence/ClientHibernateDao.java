package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.ClientDao;
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
}
