package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DocumentDao;
import ar.edu.itba.paw.models.Document;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DocumentHibernateDao extends AbstractCRUDHibernateDao<Document> implements DocumentDao {

    DocumentHibernateDao() {
        super(Document.class);
    }

    @Override
    public List<Document> findByCourseId(final long pk) {
        final TypedQuery<Document> query = em.createQuery("FROM Document as d WHERE d.course.courseid = :courseid", Document.class);
        query.setParameter("courseid", pk);
        return query.getResultList();
    }

    @Override
    public List<Document> findByClientId(final long pk) {

        final TypedQuery<Document> query = em.createQuery("FROM Document as d WHERE d.user.clientId = :clientid", Document.class);
        query.setParameter("clientid", pk);
        return query.getResultList();
    }
}
